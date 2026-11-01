/*
 * Copyright (c) 2026 Magen Gillan
 *
 * AI-based translation of lyrics using OpenAI-compatible LLM APIs.
 * Supports OpenAI, DeepSeek, Gemini (via OpenAI-compatible proxy), and OpenRouter.
 * Translates lyrics line-by-line while preserving LRC timestamps.
 */

package player.phonograph.mechanism.lyrics

import player.phonograph.model.lyrics.AbsLyrics
import player.phonograph.model.lyrics.LrcLyrics
import player.phonograph.model.lyrics.LyricsSource
import player.phonograph.settings.Keys
import player.phonograph.settings.Settings
import android.content.Context
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.put
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.util.concurrent.TimeUnit

/**
 * Translates lyrics text to the target language using an LLM API.
 *
 * All supported providers speak the OpenAI Chat Completions protocol:
 *   POST {endpoint}/v1/chat/completions
 *   Authorization: Bearer {apiKey}
 *   Body: { "model": "...", "messages": [...], "temperature": 0.3 }
 */
object AITranslator {

    private const val TAG = "AITranslator"

    private val json = Json {
        ignoreUnknownKeys = true
        encodeDefaults = false
        isLenient = true
    }

    private val httpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    /** Map provider id -> (default endpoint, default model) */
    private val providerDefaults: Map<String, Pair<String, String>> = mapOf(
        "openai"      to ("https://api.openai.com"                  to "gpt-4o-mini"),
        "deepseek"    to ("https://api.deepseek.com"                to "deepseek-chat"),
        "openrouter"  to ("https://openrouter.ai/api"               to "openai/gpt-4o-mini"),
        "gemini"      to ("https://generativelanguage.googleapis.com/v1beta/openai" to "gemini-1.5-flash"),
        "ollama"      to ("http://localhost:11434/v1"                to "llama3.1"),
        "custom"      to (""                                          to ""),
    )

    /** Language code -> human-readable name (for prompt clarity). */
    private val languageNames: Map<String, String> = mapOf(
        "ar"    to "Arabic",
        "en"    to "English",
        "es"    to "Spanish",
        "fr"    to "French",
        "de"    to "German",
        "it"    to "Italian",
        "ru"    to "Russian",
        "tr"    to "Turkish",
        "fa"    to "Persian",
        "ur"    to "Urdu",
        "hi"    to "Hindi",
        "id"    to "Indonesian",
        "zh-CN" to "Simplified Chinese",
        "zh-TW" to "Traditional Chinese",
        "ja"    to "Japanese",
        "ko"    to "Korean",
        "pt"    to "Portuguese",
        "nl"    to "Dutch",
    )

    /**
     * Translate the given lyrics. If [source] is an [LrcLyrics], timestamps are preserved
     * and only the text content is translated. Returns a new [AbsLyrics] with [LyricsSource.AIGenerated]
     * or null if translation failed / was disabled / API key was empty.
     *
     * Lines are batched (up to 60 per request) to keep token count reasonable and
     * to map responses back to their original positions reliably.
     */
    suspend fun translate(context: Context, source: AbsLyrics): AbsLyrics? =
        withContext(Dispatchers.IO) {
            val settings = Settings(context)
            val enabled = settings[Keys.enableAiTranslation].data
            if (!enabled) return@withContext null

            val apiKey = settings[Keys.aiTranslationApiKey].data.trim()
            if (apiKey.isEmpty()) {
                Log.w(TAG, "AI translation enabled but no API key configured")
                return@withContext null
            }

            val providerId = settings[Keys.aiTranslationProvider].data
            val targetLangCode = settings[Keys.aiTranslationTargetLang].data
            val modelFromSettings = settings[Keys.aiTranslationModel].data.trim()
            val endpointOverride = settings[Keys.aiTranslationApiEndpoint].data.trim()

            val (defaultEndpoint, defaultModel) = providerDefaults[providerId]
                ?: ("https://api.deepseek.com" to "deepseek-chat")
            val endpoint = endpointOverride.ifEmpty { defaultEndpoint }
            val model = modelFromSettings.ifEmpty { defaultModel }

            val targetLangName = languageNames[targetLangCode] ?: targetLangCode

            // Pull lines out (preserving order)
            val lines: List<String> = source.lyricsLineArray.toList()
            if (lines.isEmpty()) return@withContext null

            // Batch translation (60 lines per request to fit ~8k context comfortably)
            val translatedLines = mutableListOf<String>()
            val batchSize = 60
            for (batchStart in lines.indices step batchSize) {
                val batchEnd = minOf(batchStart + batchSize, lines.size)
                val batch = lines.subList(batchStart, batchEnd)
                val translated = try {
                    translateBatch(apiKey, endpoint, model, targetLangName, batch)
                } catch (e: Exception) {
                    Log.e(TAG, "Translation batch failed at offset $batchStart", e)
                    // fall back to original text for this batch so we don't drop lines
                    batch
                }
                translatedLines.addAll(translated)
            }

            // Rebuild lyrics with same timestamps but translated text
            rebuildLyrics(source, translatedLines)
        }

    /**
     * Send a batch of lines to the LLM and parse the response.
     * Returns a list of translated lines with the SAME length and order as input.
     */
    private suspend fun translateBatch(
        apiKey: String,
        endpoint: String,
        model: String,
        targetLangName: String,
        lines: List<String>,
    ): List<String> {
        // Compose a system prompt that instructs the LLM to return ONE translation per input line,
        // separated by "|||" so we can split reliably.
        val numberedInput = lines.withIndex().joinToString("\n") { (i, line) ->
            val safe = line.ifBlank { "(instrumental / no vocals)" }
            "${i + 1}. $safe"
        }

        val systemPrompt = """
            You are a professional song lyrics translator.
            Translate each input line into $targetLangName.
            Preserve the original meaning, tone, and poetic style.
            If a line is "(instrumental / no vocals)" or empty, output it unchanged.
            Output EXACTLY ${lines.size} lines, one per input line, in the same order.
            Do not include numbers, quotation marks, or extra commentary.
            Separate each translated line with the delimiter ||| on a single line.
        """.trimIndent()

        val userPrompt = "Translate these ${lines.size} lyric lines to $targetLangName:\n\n$numberedInput\n\n" +
            "Reply with ONLY the translated lines separated by '|||' (no numbering, no extra text)."

        val requestBody = buildChatCompletionRequest(model, systemPrompt, userPrompt)
        val url = endpoint.trimEnd('/') + "/v1/chat/completions"

        val request = Request.Builder()
            .url(url)
            .header("Authorization", "Bearer $apiKey")
            .header("Content-Type", "application/json")
            .header("Accept", "application/json")
            .post(requestBody.toRequestBody("application/json".toMediaType()))
            .build()

        httpClient.newCall(request).execute().use { response ->
            if (!response.isSuccessful) {
                val errBody = response.body?.string().orEmpty()
                throw RuntimeException("HTTP ${response.code}: ${errBody.take(500)}")
            }
            val raw = response.body?.string().orEmpty()
            val content = parseChatCompletionContent(raw)
            return splitTranslations(content, lines.size)
        }
    }

    private fun buildChatCompletionRequest(
        model: String,
        systemPrompt: String,
        userPrompt: String,
    ): String {
        val messages: JsonArray = buildJsonArray {
            add(buildJsonObject {
                put("role", "system")
                put("content", systemPrompt)
            })
            add(buildJsonObject {
                put("role", "user")
                put("content", userPrompt)
            })
        }
        val obj: JsonObject = buildJsonObject {
            put("model", model)
            put("messages", messages)
            put("temperature", 0.3)
            put("max_tokens", 4096)
        }
        return json.encodeToString(JsonObject.serializer(), obj)
    }

    /** Extract the assistant message content from a chat completion JSON response. */
    private fun parseChatCompletionContent(raw: String): String {
        val root = json.parseToJsonElement(raw).jsonObject
        val choices = root["choices"]?.jsonArray
            ?: throw RuntimeException("Malformed response: no choices")
        val firstChoice = choices.firstOrNull()
            ?: throw RuntimeException("Empty choices array")
        val message = firstChoice.jsonObject["message"]?.jsonObject
            ?: throw RuntimeException("No message in first choice")
        return message["content"]?.jsonPrimitive?.contentOrNull.orEmpty()
    }

    /**
     * Split the LLM response into exactly [expectedSize] translated lines.
     * Handles common LLM formatting quirks (extra whitespace, numbered responses, etc.).
     */
    private fun splitTranslations(raw: String, expectedSize: Int): List<String> {
        // Primary: split by our delimiter "|||"
        val byDelimiter = raw.split("|||")
        if (byDelimiter.size >= expectedSize) {
            return byDelimiter.take(expectedSize).map { it.trim().trim('"').trim() }
        }
        // Fallback: split by newline (LLM may have ignored the delimiter)
        val byNewline = raw.split("\n").map { it.trim() }.filter { it.isNotEmpty() }
        // Strip leading "N. " numbering if present
        val stripped = byNewline.map { line ->
            val regex = Regex("""^\s*\d+\.\s*(.*)$""")
            regex.matchEntire(line)?.groupValues?.getOrNull(1)?.takeIf { it.isNotBlank() } ?: line
        }
        return if (stripped.size >= expectedSize) {
            stripped.take(expectedSize)
        } else {
            // Pad with empty strings if LLM returned fewer lines than requested
            stripped + List(expectedSize - stripped.size) { "" }
        }
    }

    /**
     * Rebuild the AbsLyrics with translated text. For LRC, preserves the SparseArray
     * timestamps; for plain text, just returns a new text lyrics.
     */
    private fun rebuildLyrics(source: AbsLyrics, translatedLines: List<String>): AbsLyrics {
        return when (source) {
            is ActualLrcLyrics -> {
                // Reconstruct LRC with translated lines (timestamps preserved)
                val originalLines = source.lyricsLineArray
                val times = source.lyricsTimeArray
                if (translatedLines.size < originalLines.size) {
                    Log.w(TAG, "Translated lines (${translatedLines.size}) < original (${originalLines.size}); padding")
                }
                val paddedTranslations = translatedLines + List(
                    maxOf(0, originalLines.size - translatedLines.size)
                ) { "" }
                // Build raw LRC text with translated lines + timestamps
                val sb = StringBuilder()
                // Preserve metadata header (offset, length, title) if present
                if (source.title != AbsLyrics.DEFAULT_TITLE) {
                    sb.append("[ti:").append(source.title).append("]\n")
                }
                if (source.totalTime > 0) {
                    sb.append("[length:").append(formatLrcTime(source.totalTime)).append("]\n")
                }
                if (source.offset != 0L) {
                    sb.append("[offset:").append(source.offset).append("]\n")
                }
                for (i in originalLines.indices) {
                    val ts = times[i]
                    val text = paddedTranslations[i]
                    sb.append("[")
                        .append(formatLrcTime(ts.toLong()))
                        .append("]")
                        .append(text)
                        .append("\n")
                }
                LyricsLoader.parse(sb.toString(), LyricsSource.AIGenerated)
            }
            is ActualTextLyrics -> {
                // Plain text: join translated lines with newlines
                val raw = translatedLines.joinToString("\n")
                LyricsLoader.parse(raw, LyricsSource.AIGenerated)
            }
            else -> {
                // Unknown type — rebuild as text
                val raw = translatedLines.joinToString("\n")
                LyricsLoader.parse(raw, LyricsSource.AIGenerated)
            }
        }
    }

    /** Format millisecond timestamp as [mm:ss.xx] for LRC. */
    private fun formatLrcTime(ms: Long): String {
        val totalSeconds = ms / 1000.0
        val minutes = (totalSeconds / 60).toInt()
        val seconds = totalSeconds - minutes * 60
        return "%02d:%05.2f".format(minutes, seconds)
    }
}

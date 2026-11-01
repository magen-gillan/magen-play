/*
 *  Copyright (c) 2022~2026 chr_56
 */

package player.phonograph.ui.modules.setting

import player.phonograph.R
import player.phonograph.mechanism.StatusBarLyric
import player.phonograph.model.lyrics.LYRICS_ALIGN_CENTER
import player.phonograph.model.lyrics.LYRICS_ALIGN_LEFT
import player.phonograph.model.lyrics.LYRICS_ALIGN_RIGHT
import player.phonograph.settings.Keys
import player.phonograph.ui.modules.setting.components.BooleanPreference
import player.phonograph.ui.modules.setting.components.DialogPreference
import player.phonograph.ui.modules.setting.components.FloatPreference
import player.phonograph.ui.modules.setting.components.ListPreference
import player.phonograph.ui.modules.setting.components.SettingsGroup
import player.phonograph.ui.modules.setting.dialog.AiTranslationAdvancedDialog
import player.phonograph.ui.modules.setting.dialog.AiTranslationApiKeyDialog
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun PreferenceScreenLyrics() {
    val context = LocalContext.current
    Column(
        Modifier.verticalScroll(rememberScrollState())
    ) {
        Spacer(Modifier.height(16.dp))
        BooleanPreference(
            key = Keys.enableLyrics,
            titleRes = R.string.pref_title_load_lyrics,
            summaryRes = R.string.pref_summary_load_lyrics,
        )
        SettingsGroup(titleRes = R.string.pref_header_lyrics) {
            BooleanPreference(
                key = Keys.synchronizedLyricsShow,
                titleRes = R.string.pref_title_synchronized_lyrics_show,
                summaryRes = R.string.pref_summary_synchronized_lyrics_show,
                onValueChanged = { newValue ->
                    if (!newValue) {
                        // clear lyrics displaying on the status bar now
                        StatusBarLyric.stopLyric()
                    }
                }
            )
            BooleanPreference(
                key = Keys.broadcastSynchronizedLyrics,
                titleRes = R.string.pref_title_send_lyrics,
                summaryRes = R.string.pref_summary_send_lyrics,
            )
        }
        SettingsGroup(titleRes = R.string.pref_header_appearance) {
            FloatPreference(
                key = Keys.coverLyricsSize,
                valueRange = 10f..28f,
                steps = 8,
                titleRes = R.string.pref_title_lyrics_size_cover,
                summaryRes = R.string.pref_summary_lyrics_size_cover,
            )
            ListPreference(
                key = Keys.coverLyricsAlign,
                optionsValues = listOf(
                    LYRICS_ALIGN_LEFT,
                    LYRICS_ALIGN_RIGHT,
                    LYRICS_ALIGN_CENTER
                ),
                optionsValuesLocalized = listOf(
                    R.string.pref_value_align_left,
                    R.string.pref_value_align_right,
                    R.string.pref_value_align_center,
                ),
                titleRes = R.string.pref_title_lyrics_align_cover,
                summaryRes = R.string.pref_summary_lyrics_align_cover,
            )
            FloatPreference(
                key = Keys.dialogLyricsSize,
                valueRange = 8f..26f,
                steps = 8,
                titleRes = R.string.pref_title_lyrics_size_dialog,
                summaryRes = R.string.pref_summary_lyrics_size_dialog,
            )
            BooleanPreference(
                key = Keys.displaySynchronizedLyricsTimeAxis,
                titleRes = R.string.pref_title_display_lyrics_time_axis,
                summaryRes = R.string.pref_summary_display_lyrics_time_axis,
            )
        }
        // AI Translation settings
        SettingsGroup(titleRes = R.string.pref_header_ai_translation) {
            BooleanPreference(
                key = Keys.enableAiTranslation,
                titleRes = R.string.pref_title_enable_ai_translation,
                summaryRes = R.string.pref_summary_enable_ai_translation,
            )
            // The following preferences are only enabled when AI translation is on
            val aiEnabled = dependOn(Keys.enableAiTranslation) { it }
            ListPreference(
                key = Keys.aiTranslationProvider,
                optionsValues = listOf(
                    "deepseek", "openai", "openrouter", "gemini", "ollama", "custom"
                ),
                optionsValuesLocalized = listOf(
                    R.string.ai_provider_deepseek,
                    R.string.ai_provider_openai,
                    R.string.ai_provider_openrouter,
                    R.string.ai_provider_gemini,
                    R.string.ai_provider_ollama,
                    R.string.ai_provider_custom,
                ),
                titleRes = R.string.pref_title_ai_provider,
                summaryRes = R.string.pref_summary_ai_provider,
                enabled = aiEnabled,
            )
            DialogPreference(
                dialog = AiTranslationApiKeyDialog::class.java,
                titleRes = R.string.pref_title_ai_api_key,
                summaryRes = R.string.pref_summary_ai_api_key,
                enabled = aiEnabled,
                currentValueForHint = { ctx ->
                    val key = Keys.aiTranslationApiKey
                    val v = player.phonograph.settings.Settings(ctx)[key].data
                    if (v.isBlank()) null
                    else "••••" + v.takeLast(4)
                },
            )
            ListPreference(
                key = Keys.aiTranslationTargetLang,
                optionsValues = listOf(
                    "ar", "en", "es", "fr", "de", "it", "ru", "tr",
                    "fa", "ur", "hi", "id", "zh-CN", "zh-TW", "ja", "ko",
                    "pt", "nl"
                ),
                optionsValuesLocalized = listOf(
                    R.string.lang_ar, R.string.lang_en, R.string.lang_es, R.string.lang_fr,
                    R.string.lang_de, R.string.lang_it, R.string.lang_ru, R.string.lang_tr,
                    R.string.lang_fa, R.string.lang_ur, R.string.lang_hi, R.string.lang_id,
                    R.string.lang_zh_cn, R.string.lang_zh_tw, R.string.lang_ja, R.string.lang_ko,
                    R.string.lang_pt, R.string.lang_nl
                ),
                titleRes = R.string.pref_title_ai_target_lang,
                summaryRes = R.string.pref_summary_ai_target_lang,
                enabled = aiEnabled,
            )
            DialogPreference(
                dialog = AiTranslationAdvancedDialog::class.java,
                titleRes = R.string.pref_title_ai_advanced,
                summaryRes = R.string.pref_summary_ai_advanced,
                enabled = aiEnabled,
                currentValueForHint = { ctx ->
                    val modelKey = Keys.aiTranslationModel
                    val endpointKey = Keys.aiTranslationApiEndpoint
                    val model = player.phonograph.settings.Settings(ctx)[modelKey].data
                    val endpoint = player.phonograph.settings.Settings(ctx)[endpointKey].data
                    when {
                        model.isNotBlank() && endpoint.isNotBlank() -> "$model @ $endpoint"
                        model.isNotBlank() -> model
                        endpoint.isNotBlank() -> endpoint
                        else -> null
                    }
                },
            )
        }
        Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.systemBars))
    }
}

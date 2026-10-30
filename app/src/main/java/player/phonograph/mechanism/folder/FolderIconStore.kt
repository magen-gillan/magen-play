/*
 *  Copyright (c) 2022~2026 chr_56
 */

package player.phonograph.mechanism.folder

import player.phonograph.model.SongCollection
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.IOException
import java.security.MessageDigest

/**
 * Persistent store of custom folder icons.
 *
 * The picked image is **copied** into the app-private storage (`filesDir/folder_icons`),
 * so it never depends on the (temporary) URI permission of the gallery app and survives restarts.
 * The folder is identified by its full path ([SongCollection.folderKey]), not by its (non-unique) short name.
 */
object FolderIconStore {

    private const val TAG = "FolderIconStore"
    private const val PREFS_NAME = "custom_folder_icons"
    private const val DIRECTORY = "folder_icons"
    private const val MAX_SIZE = 768

    private fun preferences(context: Context): SharedPreferences =
        context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    private fun directory(context: Context): File =
        File(context.applicationContext.filesDir, DIRECTORY).also { it.mkdirs() }

    /**
     * @return the saved icon file for [folderKey], null if not set (or file is lost)
     */
    fun get(context: Context, folderKey: String): File? {
        val fileName = preferences(context).getString(folderKey, null) ?: return null
        val file = File(directory(context), fileName)
        return if (file.isFile && file.length() > 0) file else null
    }

    fun get(context: Context, folder: SongCollection): File? = get(context, folder.folderKey)

    /**
     * Copy and save image from [uri] as the icon of [folderKey]
     * @return true if succeeded
     */
    suspend fun set(context: Context, folderKey: String, uri: Uri): Boolean = withContext(Dispatchers.IO) {
        val app = context.applicationContext
        try {
            val bitmap = decodeScaled(app, uri) ?: return@withContext false
            val dir = directory(app)
            // a new file name for each change so that image caches never serve the old image
            val newFile = File(dir, "${digest(folderKey)}_${System.currentTimeMillis()}.jpg")
            try {
                newFile.outputStream().buffered().use { out ->
                    if (!bitmap.compress(Bitmap.CompressFormat.JPEG, 92, out)) return@withContext false
                }
            } finally {
                bitmap.recycle()
            }
            val prefs = preferences(app)
            val old = prefs.getString(folderKey, null)
            // commit() (synchronous) to make sure it is really written before reporting success
            val ok = prefs.edit().putString(folderKey, newFile.name).commit()
            if (ok && old != null && old != newFile.name) File(dir, old).delete()
            if (!ok) newFile.delete()
            ok
        } catch (e: IOException) {
            Log.w(TAG, "Failed to save folder icon", e)
            false
        } catch (e: SecurityException) {
            Log.w(TAG, "No permission to read the image", e)
            false
        } catch (e: OutOfMemoryError) {
            Log.w(TAG, "Image is too large", e)
            false
        }
    }

    /**
     * Remove the custom icon of [folderKey]
     */
    suspend fun remove(context: Context, folderKey: String) = withContext(Dispatchers.IO) {
        val app = context.applicationContext
        val prefs = preferences(app)
        val old = prefs.getString(folderKey, null)
        prefs.edit().remove(folderKey).commit()
        if (old != null) File(directory(app), old).delete()
    }

    private fun decodeScaled(context: Context, uri: Uri): Bitmap? {
        val resolver = context.contentResolver
        val bounds = BitmapFactory.Options().apply { inJustDecodeBounds = true }
        resolver.openInputStream(uri)?.use { BitmapFactory.decodeStream(it, null, bounds) } ?: return null
        if (bounds.outWidth <= 0 || bounds.outHeight <= 0) return null

        var sample = 1
        while (bounds.outWidth / (sample * 2) >= MAX_SIZE && bounds.outHeight / (sample * 2) >= MAX_SIZE) sample *= 2
        val options = BitmapFactory.Options().apply { inSampleSize = sample }
        return resolver.openInputStream(uri)?.use { BitmapFactory.decodeStream(it, null, options) }
    }

    private fun digest(text: String): String =
        MessageDigest.getInstance("SHA-1").digest(text.toByteArray())
            .joinToString("") { "%02x".format(it) }.take(16)
}

/**
 * Unique key of a folder: its full path, or its name if the path is unknown
 */
val SongCollection.folderKey: String get() = detail?.takeIf { it.isNotEmpty() } ?: name

package com.onlydevs.artiko.data

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import java.io.File
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/** A file picked or recorded on the phone, read into memory to be sent to the API. */
data class MediaFile(val name: String, val mimeType: String, val bytes: ByteArray)

fun Context.displayName(uri: Uri): String =
    contentResolver.query(uri, arrayOf(OpenableColumns.DISPLAY_NAME), null, null, null)
        ?.use { cursor -> if (cursor.moveToFirst()) cursor.getString(0) else null }
        ?: uri.lastPathSegment
        ?: "file"

suspend fun Context.readMedia(uri: Uri): MediaFile = withContext(Dispatchers.IO) {
    val name = displayName(uri)
    val bytes = contentResolver.openInputStream(uri)?.use { it.readBytes() } ?: error("Cannot read $uri")
    MediaFile(name, contentResolver.getType(uri) ?: "application/octet-stream", bytes)
}

suspend fun File.readMedia(mimeType: String): MediaFile = withContext(Dispatchers.IO) {
    MediaFile(name, mimeType, readBytes())
}

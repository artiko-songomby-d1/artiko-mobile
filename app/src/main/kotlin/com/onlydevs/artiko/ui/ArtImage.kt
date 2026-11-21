package com.onlydevs.artiko.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.size.Size
import com.onlydevs.artiko.ui.theme.LocalAppSettings

private const val SAVER_SIDE = 480

/** A picture from the network; with the data saver on it is loaded at a small size. */
@Composable
fun ArtImage(
    url: String,
    contentDescription: String?,
    modifier: Modifier = Modifier.fillMaxSize(),
    contentScale: ContentScale = ContentScale.Crop,
) {
    val saver = LocalAppSettings.current.dataSaver
    val request = ImageRequest.Builder(LocalContext.current)
        .data(url)
        .apply { if (saver) size(Size(SAVER_SIDE, SAVER_SIDE)) }
        .build()
    AsyncImage(model = request, contentDescription = contentDescription, modifier = modifier, contentScale = contentScale)
}

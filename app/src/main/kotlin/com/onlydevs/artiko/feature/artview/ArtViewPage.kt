package com.onlydevs.artiko.feature.artview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.onlydevs.artiko.data.MediaKind
import com.onlydevs.artiko.data.Work
import com.onlydevs.artiko.ui.FEED_PADDING
import com.onlydevs.artiko.ui.FEED_SPACING
import com.onlydevs.artiko.ui.theme.ArtikoTheme

private val UNSUPPORTED_VIEWER_HEIGHT = 240.dp

@Composable
fun ArtViewPage(work: Work, onExit: () -> Unit, onOpenArtist: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(FEED_PADDING),
        verticalArrangement = Arrangement.spacedBy(FEED_SPACING),
    ) {
        TextButton(onClick = onExit) { Text("Exit") }
        ArtMedia(work)
        Text(work.title, style = MaterialTheme.typography.headlineMedium)
        TextButton(onClick = { onOpenArtist(work.artistId) }) { Text(work.artistName) }
        if (work.humanProven) {
            Text("Human-made", style = MaterialTheme.typography.labelLarge)
        }
        Text(work.story)
    }
}

@Composable
private fun ArtMedia(work: Work) {
    if (work.mediaKind == MediaKind.Image) {
        AsyncImage(
            model = work.mediaUrl,
            contentDescription = work.title,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth(),
        )
    } else {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(UNSUPPORTED_VIEWER_HEIGHT)
                .background(MaterialTheme.colorScheme.surfaceVariant),
            contentAlignment = Alignment.Center,
        ) {
            Text("Viewer for ${work.mediaKind.name.lowercase()} is coming soon")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ArtViewPagePreview() {
    val work = Work("1", "1", "Rado", "Sunrise", "Painted at dawn", "painting", "", MediaKind.Video, true, 3)
    ArtikoTheme {
        ArtViewPage(work, onExit = {}, onOpenArtist = {})
    }
}

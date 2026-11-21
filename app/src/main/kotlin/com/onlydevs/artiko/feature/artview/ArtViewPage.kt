package com.onlydevs.artiko.feature.artview

import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.onlydevs.artiko.data.MediaKind
import com.onlydevs.artiko.data.Work
import com.onlydevs.artiko.ui.ArtImage
import com.onlydevs.artiko.ui.ExpositionChip
import com.onlydevs.artiko.ui.FEED_PADDING
import com.onlydevs.artiko.ui.FEED_SPACING
import com.onlydevs.artiko.ui.GeneratedCover
import com.onlydevs.artiko.ui.KindChip
import com.onlydevs.artiko.ui.rememberHapticClick
import com.onlydevs.artiko.ui.theme.ArtikoText
import com.onlydevs.artiko.ui.theme.ArtikoTheme
import com.onlydevs.artiko.ui.tiltOnDrag

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
        ExpositionChip(title = "Exit", onClick = onExit)
        ArtMedia(work, Modifier.tiltOnDrag())
        Text(work.title, style = MaterialTheme.typography.headlineMedium)
        Text(
            text = work.artistName,
            style = ArtikoText.meta,
            modifier = Modifier.clickable(onClick = rememberHapticClick { onOpenArtist(work.artistId) }),
        )
        if (work.humanProven) {
            KindChip("Human-made", color = MaterialTheme.colorScheme.primary)
        }
        Text(work.story)
    }
}

@Composable
private fun ArtMedia(work: Work, modifier: Modifier = Modifier) {
    if (work.mediaKind == MediaKind.Image && work.mediaUrl.isNotBlank()) {
        ArtImage(
            url = work.mediaUrl,
            contentDescription = work.title,
            modifier = modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth,
        )
    } else {
        Box(
            modifier = modifier.fillMaxWidth().height(UNSUPPORTED_VIEWER_HEIGHT),
            contentAlignment = Alignment.Center,
        ) {
            GeneratedCover(seed = work.id)
            if (work.mediaKind != MediaKind.Image) {
                Text("Viewer for ${work.mediaKind.name.lowercase()} is coming soon", color = Color.White)
            }
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

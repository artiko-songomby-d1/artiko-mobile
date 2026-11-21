package com.onlydevs.artiko.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.onlydevs.artiko.data.MediaKind
import com.onlydevs.artiko.data.Work
import com.onlydevs.artiko.ui.theme.ArtikoTheme

private val CARD_HEIGHT = 220.dp
private val FRAME_ELEVATION = 10.dp
private const val PRESS_SCALE_DROP = 0.03f
private const val PRESS_TILT_DEGREES = 4f
private val OVERLAY_PADDING = 12.dp
private const val OVERLAY_ALPHA = 0.6f

@Composable
fun ArtCard(work: Work, onClick: () -> Unit, modifier: Modifier = Modifier) {
    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()
    val press by animateFloatAsState(targetValue = if (pressed) 1f else 0f, label = "press")
    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(CARD_HEIGHT)
            .graphicsLayer {
                scaleX = 1f - PRESS_SCALE_DROP * press
                scaleY = scaleX
                rotationX = PRESS_TILT_DEGREES * press
            },
        elevation = CardDefaults.cardElevation(defaultElevation = FRAME_ELEVATION),
        interactionSource = interactionSource,
    ) {
        Box(Modifier.fillMaxSize()) {
            ArtThumbnail(work)
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
                    .background(Color.Black.copy(alpha = OVERLAY_ALPHA))
                    .padding(OVERLAY_PADDING),
            ) {
                Text(work.title, style = MaterialTheme.typography.titleMedium, color = Color.White)
                Text(work.artistName, style = MaterialTheme.typography.bodySmall, color = Color.White)
                if (work.humanProven) {
                    Text("Human-made", style = MaterialTheme.typography.labelSmall, color = Color.White)
                }
            }
        }
    }
}

@Composable
private fun ArtThumbnail(work: Work) {
    if (work.mediaKind == MediaKind.Image && work.mediaUrl.isNotBlank()) {
        AsyncImage(
            model = work.mediaUrl,
            contentDescription = work.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )
    } else {
        Box(modifier = Modifier.fillMaxSize()) {
            GeneratedCover(seed = work.id)
            if (work.mediaKind != MediaKind.Image) {
                Text(
                    text = work.mediaKind.name.uppercase(),
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White,
                    modifier = Modifier.align(Alignment.TopEnd).padding(OVERLAY_PADDING),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ArtCardPreview() {
    ArtikoTheme {
        ArtCard(
            work = Work(
                id = "1",
                artistId = "1",
                artistName = "Rado",
                title = "Sunrise",
                story = "",
                category = "painting",
                mediaUrl = "",
                mediaKind = MediaKind.Video,
                humanProven = true,
                votes = 3,
            ),
            onClick = {},
        )
    }
}

package com.onlydevs.artiko.feature.record

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.onlydevs.artiko.ui.ArtikoButton
import com.onlydevs.artiko.ui.ExpositionChip
import com.onlydevs.artiko.ui.FEED_PADDING
import com.onlydevs.artiko.ui.FEED_SPACING
import com.onlydevs.artiko.ui.GeneratedCover
import com.onlydevs.artiko.ui.KindChip
import com.onlydevs.artiko.ui.camera.ViewfinderFrame
import com.onlydevs.artiko.ui.theme.ArtikoText
import com.onlydevs.artiko.ui.theme.ArtikoTheme

private val TIMER_MARGIN = 16.dp
private const val SECONDS_PER_MINUTE = 60

fun formatElapsed(seconds: Int): String = "%02d:%02d".format(seconds / SECONDS_PER_MINUTE, seconds % SECONDS_PER_MINUTE)

/** The record screen: a framed viewfinder with a timer, the record button and what to do with the take. */
@Composable
fun RecordPage(
    isRecording: Boolean,
    elapsedSeconds: Int,
    hasRecording: Boolean,
    viewfinder: @Composable () -> Unit,
    onToggle: () -> Unit,
    onUpload: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize().statusBarsPadding().padding(FEED_PADDING),
        verticalArrangement = Arrangement.spacedBy(FEED_SPACING),
    ) {
        Text("Record", style = MaterialTheme.typography.headlineMedium)
        Box(modifier = Modifier.weight(1f).fillMaxWidth().clip(MaterialTheme.shapes.medium)) {
            viewfinder()
            ViewfinderFrame()
            if (isRecording) {
                KindChip(
                    label = "Rec ${formatElapsed(elapsedSeconds)}",
                    modifier = Modifier.align(Alignment.TopStart).padding(TIMER_MARGIN),
                )
            }
        }
        Text("Record while you work: the take can prove the work is human-made.", style = ArtikoText.meta)
        Row(
            horizontalArrangement = Arrangement.spacedBy(FEED_SPACING),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ArtikoButton(text = if (isRecording) "Stop" else "Record", onClick = onToggle)
            if (hasRecording && !isRecording) {
                ExpositionChip(title = "Upload it", onClick = onUpload)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RecordPagePreview() {
    ArtikoTheme {
        RecordPage(
            isRecording = true,
            elapsedSeconds = 7,
            hasRecording = false,
            viewfinder = { GeneratedCover(seed = "recording") },
            onToggle = {},
            onUpload = {},
        )
    }
}

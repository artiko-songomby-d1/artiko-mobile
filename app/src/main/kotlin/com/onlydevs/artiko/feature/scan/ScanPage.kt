package com.onlydevs.artiko.feature.scan

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
import com.onlydevs.artiko.data.ScanResult
import com.onlydevs.artiko.ui.ArtikoButton
import com.onlydevs.artiko.ui.ExpositionChip
import com.onlydevs.artiko.ui.FEED_PADDING
import com.onlydevs.artiko.ui.FEED_SPACING
import com.onlydevs.artiko.ui.GeneratedCover
import com.onlydevs.artiko.ui.LoadState
import com.onlydevs.artiko.ui.camera.ViewfinderFrame
import com.onlydevs.artiko.ui.theme.ArtikoText
import com.onlydevs.artiko.ui.theme.ArtikoTheme

/** The scan screen: a framed viewfinder, then either the two ways to scan or the answer. */
@Composable
fun ScanPage(
    state: LoadState<ScanResult>?,
    viewfinder: @Composable () -> Unit,
    onScan: () -> Unit,
    onPick: () -> Unit,
    onReset: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize().statusBarsPadding().padding(FEED_PADDING),
        verticalArrangement = Arrangement.spacedBy(FEED_SPACING),
    ) {
        Text("Scan", style = MaterialTheme.typography.headlineMedium)
        Box(modifier = Modifier.weight(1f).fillMaxWidth().clip(MaterialTheme.shapes.medium)) {
            viewfinder()
            ViewfinderFrame()
        }
        when (state) {
            null -> Row(
                horizontalArrangement = Arrangement.spacedBy(FEED_SPACING),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                ArtikoButton(text = "Scan", onClick = onScan)
                ExpositionChip(title = "Gallery", onClick = onPick)
            }

            LoadState.Loading -> Text("Looking for the artist...", style = ArtikoText.meta)

            is LoadState.Failed -> {
                Text(state.message, style = ArtikoText.meta)
                ArtikoButton(text = "Try again", onClick = onReset)
            }

            is LoadState.Loaded -> {
                ScanResultCard(state.data)
                ArtikoButton(text = "Scan again", onClick = onReset)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ScanPagePreview() {
    ArtikoTheme {
        ScanPage(
            state = null,
            viewfinder = { GeneratedCover(seed = "viewfinder") },
            onScan = {},
            onPick = {},
            onReset = {},
        )
    }
}

package com.onlydevs.artiko.feature.scan

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.onlydevs.artiko.data.ScanResult
import com.onlydevs.artiko.data.ScanStatus
import com.onlydevs.artiko.ui.KindChip
import com.onlydevs.artiko.ui.theme.ArtikoText
import com.onlydevs.artiko.ui.theme.ArtikoTheme
import kotlin.math.roundToInt

private val CARD_PADDING = 16.dp
private val CARD_SPACING = 8.dp
private const val PERCENT = 100

internal fun ScanStatus.label() = when (this) {
    ScanStatus.HumanProven -> "Human-made"
    ScanStatus.ArtikoWork -> "On Artiko"
    ScanStatus.Known -> "Known work"
    ScanStatus.AiMade -> "AI-made"
    ScanStatus.Unknown -> "Not found"
}

/** The answer of a scan: who made the work, how sure the match is and where to read more. */
@Composable
fun ScanResultCard(result: ScanResult, modifier: Modifier = Modifier) {
    val title = result.work?.title ?: result.known?.title ?: "Nothing found"
    val maker = result.work?.artistName
        ?: result.known?.let { "${it.artistName}, ${it.date}" }
        ?: "Not finding a work does not mean it is fake"
    Card(modifier = modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(CARD_PADDING),
            verticalArrangement = Arrangement.spacedBy(CARD_SPACING),
        ) {
            KindChip(label = result.status.label())
            Text(text = title, style = MaterialTheme.typography.headlineSmall)
            Text(text = maker, style = ArtikoText.meta)
            if (result.status != ScanStatus.Unknown) {
                Text(
                    text = "${(result.confidence * PERCENT).roundToInt()}% match",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
            result.sources.forEach { Text(text = it.label, style = MaterialTheme.typography.bodySmall) }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ScanResultCardPreview() {
    ArtikoTheme {
        ScanResultCard(ScanResult(status = ScanStatus.Unknown, confidence = 0.0))
    }
}

package com.onlydevs.artiko.feature.record

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.onlydevs.artiko.ui.PlaceholderScreen
import com.onlydevs.artiko.ui.PlaceholderSection
import com.onlydevs.artiko.ui.theme.ArtikoTheme

@Composable
fun RecordScreen() {
    PlaceholderScreen(
        title = "Record",
        sections = listOf(
            PlaceholderSection("Preview: live camera or microphone"),
            PlaceholderSection("Record: capture the work as you make it"),
            PlaceholderSection("Proof: the recording is attached as proof of process"),
        ),
    )
}

@Preview(showBackground = true)
@Composable
private fun RecordScreenPreview() {
    ArtikoTheme {
        RecordScreen()
    }
}

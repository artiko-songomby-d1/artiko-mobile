package com.onlydevs.artiko.feature.artview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.onlydevs.artiko.ui.PlaceholderScreen
import com.onlydevs.artiko.ui.PlaceholderSection
import com.onlydevs.artiko.ui.theme.ArtikoTheme

@Composable
fun ArtViewScreen(onExit: () -> Unit) {
    PlaceholderScreen(
        title = "Art view",
        sections = listOf(
            PlaceholderSection("Exit", onExit),
            PlaceholderSection("Art canvas"),
            PlaceholderSection("Options menu"),
            PlaceholderSection("Human-made badge"),
        ),
    )
}

@Preview(showBackground = true)
@Composable
private fun ArtViewScreenPreview() {
    ArtikoTheme {
        ArtViewScreen({})
    }
}

package com.onlydevs.artiko.feature.artist

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.onlydevs.artiko.ui.PlaceholderScreen
import com.onlydevs.artiko.ui.PlaceholderSection
import com.onlydevs.artiko.ui.theme.ArtikoTheme

@Composable
fun ArtistScreen(onOpenExposition: () -> Unit, onOpenArtView: () -> Unit) {
    PlaceholderScreen(
        title = "Artist",
        sections = listOf(
            PlaceholderSection("Artist header"),
            PlaceholderSection("Expositions: open one", onOpenExposition),
            PlaceholderSection("Artist art feed: open an art", onOpenArtView),
        ),
    )
}

@Preview(showBackground = true)
@Composable
private fun ArtistScreenPreview() {
    ArtikoTheme {
        ArtistScreen({}, {})
    }
}

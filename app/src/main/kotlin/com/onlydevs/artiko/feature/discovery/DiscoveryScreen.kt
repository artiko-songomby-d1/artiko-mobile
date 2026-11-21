package com.onlydevs.artiko.feature.discovery

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.onlydevs.artiko.ui.PlaceholderScreen
import com.onlydevs.artiko.ui.PlaceholderSection
import com.onlydevs.artiko.ui.theme.ArtikoTheme

@Composable
fun DiscoveryScreen(onOpenArtist: () -> Unit, onOpenExposition: () -> Unit, onOpenArtView: () -> Unit) {
    PlaceholderScreen(
        title = "Discovery",
        sections = listOf(
            PlaceholderSection("Search bar with scan shortcut"),
            PlaceholderSection("Hall of Fame expo", onOpenExposition),
            PlaceholderSection("Favorite expos", onOpenExposition),
            PlaceholderSection("Art feed: open an art", onOpenArtView),
            PlaceholderSection("Artist of an art: open the artist", onOpenArtist),
        ),
    )
}

@Preview(showBackground = true)
@Composable
private fun DiscoveryScreenPreview() {
    ArtikoTheme {
        DiscoveryScreen({}, {}, {})
    }
}

package com.onlydevs.artiko.feature.exposition

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.onlydevs.artiko.ui.PlaceholderScreen
import com.onlydevs.artiko.ui.PlaceholderSection
import com.onlydevs.artiko.ui.theme.ArtikoTheme

@Composable
fun ExpositionScreen(onOpenArtView: () -> Unit) {
    PlaceholderScreen(
        title = "Exposition",
        sections = listOf(
            PlaceholderSection("Exposition header"),
            PlaceholderSection("Exposition art feed: open an art", onOpenArtView),
        ),
    )
}

@Preview(showBackground = true)
@Composable
private fun ExpositionScreenPreview() {
    ArtikoTheme {
        ExpositionScreen({})
    }
}

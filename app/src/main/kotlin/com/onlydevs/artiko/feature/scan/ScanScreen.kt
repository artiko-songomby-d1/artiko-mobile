package com.onlydevs.artiko.feature.scan

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.onlydevs.artiko.ui.PlaceholderScreen
import com.onlydevs.artiko.ui.PlaceholderSection
import com.onlydevs.artiko.ui.theme.ArtikoTheme

@Composable
fun ScanScreen() {
    PlaceholderScreen(
        title = "Scan",
        sections = listOf(
            PlaceholderSection("Viewfinder: point the camera at an artwork or let it listen to a song"),
            PlaceholderSection("Take a photo"),
            PlaceholderSection("Pick one from the gallery"),
            PlaceholderSection("Result: who made it and whether it is proven human-made"),
        ),
    )
}

@Preview(showBackground = true)
@Composable
private fun ScanScreenPreview() {
    ArtikoTheme {
        ScanScreen()
    }
}

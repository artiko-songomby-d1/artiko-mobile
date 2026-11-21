package com.onlydevs.artiko.feature.create

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.onlydevs.artiko.ui.PlaceholderScreen
import com.onlydevs.artiko.ui.PlaceholderSection
import com.onlydevs.artiko.ui.theme.ArtikoTheme

@Composable
fun CreateScreen() {
    PlaceholderScreen(
        title = "Create",
        sections = listOf(
            PlaceholderSection("Scan"),
            PlaceholderSection("Upload"),
            PlaceholderSection("Record"),
            PlaceholderSection("Drafts"),
        ),
    )
}

@Preview(showBackground = true)
@Composable
private fun CreateScreenPreview() {
    ArtikoTheme {
        CreateScreen()
    }
}

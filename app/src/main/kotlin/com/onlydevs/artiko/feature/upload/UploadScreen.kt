package com.onlydevs.artiko.feature.upload

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.onlydevs.artiko.ui.PlaceholderScreen
import com.onlydevs.artiko.ui.PlaceholderSection
import com.onlydevs.artiko.ui.theme.ArtikoTheme

@Composable
fun UploadScreen() {
    PlaceholderScreen(
        title = "Upload",
        sections = listOf(
            PlaceholderSection("1 Input: pick an existing file"),
            PlaceholderSection("2 Details: title and story"),
            PlaceholderSection("3 Exposition: choose where it hangs"),
            PlaceholderSection("4 Publish: add the proof of process"),
        ),
    )
}

@Preview(showBackground = true)
@Composable
private fun UploadScreenPreview() {
    ArtikoTheme {
        UploadScreen()
    }
}

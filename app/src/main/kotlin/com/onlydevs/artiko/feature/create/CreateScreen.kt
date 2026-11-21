package com.onlydevs.artiko.feature.create

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.onlydevs.artiko.ui.PlaceholderScreen
import com.onlydevs.artiko.ui.PlaceholderSection
import com.onlydevs.artiko.ui.theme.ArtikoTheme

@Composable
fun CreateScreen(onScan: () -> Unit, onUpload: () -> Unit, onRecord: () -> Unit) {
    PlaceholderScreen(
        title = "Create",
        sections = listOf(
            PlaceholderSection("Scan", onScan),
            PlaceholderSection("Upload", onUpload),
            PlaceholderSection("Record", onRecord),
            PlaceholderSection("Drafts"),
        ),
    )
}

@Preview(showBackground = true)
@Composable
private fun CreateScreenPreview() {
    ArtikoTheme {
        CreateScreen(onScan = {}, onUpload = {}, onRecord = {})
    }
}

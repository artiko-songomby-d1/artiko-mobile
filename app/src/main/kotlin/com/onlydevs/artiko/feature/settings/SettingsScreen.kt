package com.onlydevs.artiko.feature.settings

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.onlydevs.artiko.ui.PlaceholderScreen
import com.onlydevs.artiko.ui.PlaceholderSection
import com.onlydevs.artiko.ui.theme.ArtikoTheme

@Composable
fun SettingsScreen() {
    PlaceholderScreen(
        title = "Settings",
        sections = listOf(
            PlaceholderSection("Appearance"),
            PlaceholderSection("Accessibility"),
            PlaceholderSection("Data"),
            PlaceholderSection("Account"),
            PlaceholderSection("About"),
        ),
    )
}

@Preview(showBackground = true)
@Composable
private fun SettingsScreenPreview() {
    ArtikoTheme {
        SettingsScreen()
    }
}

package com.onlydevs.artiko.feature.learn

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.onlydevs.artiko.ui.PlaceholderScreen
import com.onlydevs.artiko.ui.PlaceholderSection
import com.onlydevs.artiko.ui.theme.ArtikoTheme

@Composable
fun LearnScreen() {
    PlaceholderScreen(
        title = "Learn",
        sections = listOf(
            PlaceholderSection("Craft filters"),
            PlaceholderSection("Lessons"),
        ),
    )
}

@Preview(showBackground = true)
@Composable
private fun LearnScreenPreview() {
    ArtikoTheme {
        LearnScreen()
    }
}

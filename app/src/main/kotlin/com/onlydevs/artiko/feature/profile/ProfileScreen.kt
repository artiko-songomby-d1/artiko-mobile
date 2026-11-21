package com.onlydevs.artiko.feature.profile

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.onlydevs.artiko.ui.PlaceholderScreen
import com.onlydevs.artiko.ui.PlaceholderSection
import com.onlydevs.artiko.ui.theme.ArtikoTheme

@Composable
fun ProfileScreen(onOpenSettings: () -> Unit) {
    PlaceholderScreen(
        title = "Profile",
        sections = listOf(
            PlaceholderSection("Settings", onOpenSettings),
            PlaceholderSection("Favorite expos"),
            PlaceholderSection("Saved arts"),
            PlaceholderSection("My works"),
        ),
    )
}

@Preview(showBackground = true)
@Composable
private fun ProfileScreenPreview() {
    ArtikoTheme {
        ProfileScreen({})
    }
}

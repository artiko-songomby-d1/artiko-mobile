package com.onlydevs.artiko.feature.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

enum class TopLevelTab(val route: String, val label: String, val icon: ImageVector) {
    Discovery("discovery", "Discovery", Icons.Filled.Home),
    Create("create", "Create", Icons.Filled.Add),
    Learn("learn", "Learn", Icons.Filled.Star),
    Profile("profile", "Profile", Icons.Filled.Person),
}

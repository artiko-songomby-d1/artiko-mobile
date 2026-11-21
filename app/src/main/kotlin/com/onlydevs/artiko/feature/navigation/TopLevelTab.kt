package com.onlydevs.artiko.feature.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

enum class TopLevelTab(val route: Any, val label: String, val icon: ImageVector) {
    Discovery(DiscoveryRoute, "Discovery", Icons.Filled.Home),
    Create(CreateRoute, "Create", Icons.Filled.Add),
    Learn(LearnRoute, "Learn", Icons.Filled.Star),
    Profile(ProfileRoute, "Profile", Icons.Filled.Person),
}

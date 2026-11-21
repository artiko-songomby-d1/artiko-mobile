package com.onlydevs.artiko

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.onlydevs.artiko.feature.navigation.ArtikoBottomBar
import com.onlydevs.artiko.feature.navigation.ArtikoNavHost
import com.onlydevs.artiko.feature.navigation.TopLevelTab
import com.onlydevs.artiko.feature.navigation.navigateToTab

@Composable
fun ArtikoApp() {
    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val isTopLevel = TopLevelTab.entries.any { it.route == currentRoute }

    Scaffold(
        bottomBar = {
            if (isTopLevel) {
                ArtikoBottomBar(currentRoute, onTabSelected = navController::navigateToTab)
            }
        },
    ) { innerPadding ->
        ArtikoNavHost(navController, Modifier.padding(innerPadding))
    }
}

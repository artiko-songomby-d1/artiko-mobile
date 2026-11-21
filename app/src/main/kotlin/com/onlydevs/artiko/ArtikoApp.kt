package com.onlydevs.artiko

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.hasRoute
import com.onlydevs.artiko.feature.navigation.ArtikoBottomBar
import com.onlydevs.artiko.feature.navigation.ArtikoNavHost
import com.onlydevs.artiko.feature.navigation.TopLevelTab
import com.onlydevs.artiko.feature.navigation.navigateToTab

@Composable
fun ArtikoApp() {
    val navController = rememberNavController()
    val destination = navController.currentBackStackEntryAsState().value?.destination
    val selectedTab = TopLevelTab.entries.firstOrNull { tab ->
        destination?.hierarchy?.any { it.hasRoute(tab.route::class) } == true
    }

    Scaffold(
        bottomBar = {
            if (selectedTab != null) {
                ArtikoBottomBar(selectedTab, onTabSelected = navController::navigateToTab)
            }
        },
    ) { innerPadding ->
        ArtikoNavHost(navController, Modifier.padding(innerPadding))
    }
}

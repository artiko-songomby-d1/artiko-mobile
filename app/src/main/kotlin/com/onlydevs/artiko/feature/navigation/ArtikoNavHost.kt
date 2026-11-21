package com.onlydevs.artiko.feature.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.onlydevs.artiko.feature.artist.ArtistScreen
import com.onlydevs.artiko.feature.artview.ArtViewScreen
import com.onlydevs.artiko.feature.create.CreateScreen
import com.onlydevs.artiko.feature.discovery.DiscoveryScreen
import com.onlydevs.artiko.feature.exposition.ExpositionScreen
import com.onlydevs.artiko.feature.learn.LearnScreen
import com.onlydevs.artiko.feature.profile.ProfileScreen
import com.onlydevs.artiko.feature.settings.SettingsScreen

@Composable
fun ArtikoNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    val openArtist = { navController.navigate(Routes.ARTIST) }
    val openExposition = { navController.navigate(Routes.EXPOSITION) }
    val openArtView = { navController.navigate(Routes.ART_VIEW) }

    NavHost(
        navController = navController,
        startDestination = TopLevelTab.Discovery.route,
        modifier = modifier,
    ) {
        composable(TopLevelTab.Discovery.route) { DiscoveryScreen(openArtist, openExposition, openArtView) }
        composable(TopLevelTab.Create.route) { CreateScreen() }
        composable(TopLevelTab.Learn.route) { LearnScreen() }
        composable(TopLevelTab.Profile.route) {
            ProfileScreen(onOpenSettings = { navController.navigate(Routes.SETTINGS) })
        }
        composable(Routes.ARTIST) { ArtistScreen(openExposition, openArtView) }
        composable(Routes.EXPOSITION) { ExpositionScreen(openArtView) }
        composable(Routes.ART_VIEW) { ArtViewScreen(onExit = { navController.popBackStack() }) }
        composable(Routes.SETTINGS) { SettingsScreen() }
    }
}

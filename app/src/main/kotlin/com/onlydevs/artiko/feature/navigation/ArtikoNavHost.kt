package com.onlydevs.artiko.feature.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.onlydevs.artiko.feature.artist.ArtistScreen
import com.onlydevs.artiko.feature.artview.ArtViewScreen
import com.onlydevs.artiko.feature.create.CreateScreen
import com.onlydevs.artiko.feature.discovery.DiscoveryScreen
import com.onlydevs.artiko.feature.exposition.ExpositionScreen
import com.onlydevs.artiko.feature.halloffame.HallOfFameScreen
import com.onlydevs.artiko.feature.learn.LearnScreen
import com.onlydevs.artiko.feature.profile.ProfileScreen
import com.onlydevs.artiko.feature.settings.SettingsScreen

@Composable
fun ArtikoNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    val openArtView = { workId: String -> navController.navigate(ArtViewRoute(workId)) }
    val openExposition = { expositionId: String -> navController.navigate(ExpositionRoute(expositionId)) }

    NavHost(navController = navController, startDestination = DiscoveryRoute, modifier = modifier) {
        composable<DiscoveryRoute> {
            DiscoveryScreen(
                onOpenHallOfFame = { navController.navigate(HallOfFameRoute) },
                onOpenExposition = openExposition,
                onOpenArtView = openArtView,
            )
        }
        composable<CreateRoute> { CreateScreen() }
        composable<LearnRoute> { LearnScreen() }
        composable<ProfileRoute> {
            ProfileScreen(onOpenSettings = { navController.navigate(SettingsRoute) })
        }
        composable<HallOfFameRoute> { HallOfFameScreen(onOpenArtView = openArtView) }
        composable<ExpositionRoute> { entry ->
            ExpositionScreen(entry.toRoute<ExpositionRoute>().expositionId, openArtView)
        }
        composable<ArtistRoute> { entry ->
            ArtistScreen(entry.toRoute<ArtistRoute>().artistId, openExposition, openArtView)
        }
        composable<ArtViewRoute> { entry ->
            ArtViewScreen(
                workId = entry.toRoute<ArtViewRoute>().workId,
                onExit = { navController.popBackStack() },
                onOpenArtist = { artistId -> navController.navigate(ArtistRoute(artistId)) },
            )
        }
        composable<SettingsRoute> { SettingsScreen() }
    }
}

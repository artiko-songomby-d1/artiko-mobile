package com.onlydevs.artiko.feature.discovery

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.onlydevs.artiko.ui.LoadStateContent
import com.onlydevs.artiko.ui.artikoViewModel

@Composable
fun DiscoveryScreen(
    onOpenHallOfFame: () -> Unit,
    onOpenExposition: (String) -> Unit,
    onOpenArtView: (String) -> Unit,
) {
    val viewModel = artikoViewModel { api -> DiscoveryViewModel(api) }
    val state by viewModel.state.collectAsStateWithLifecycle()
    val query by viewModel.query.collectAsStateWithLifecycle()
    LoadStateContent(state = state, onRetry = viewModel::reload) { content ->
        DiscoveryPage(content, query, viewModel::search, onOpenHallOfFame, onOpenExposition, onOpenArtView)
    }
}

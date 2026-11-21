package com.onlydevs.artiko.feature.artist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.onlydevs.artiko.ui.LoadStateContent
import com.onlydevs.artiko.ui.artikoViewModel

@Composable
fun ArtistScreen(
    artistId: String,
    onBack: () -> Unit,
    onOpenExposition: (String) -> Unit,
    onOpenArtView: (String) -> Unit,
) {
    val viewModel = artikoViewModel { api -> ArtistViewModel(api, artistId) }
    val state by viewModel.state.collectAsStateWithLifecycle()
    LoadStateContent(state = state, onRetry = viewModel::reload) { content ->
        ArtistPage(content, onOpenExposition, onOpenArtView, onBack)
    }
}

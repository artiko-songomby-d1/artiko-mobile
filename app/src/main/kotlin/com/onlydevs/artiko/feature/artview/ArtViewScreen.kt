package com.onlydevs.artiko.feature.artview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.onlydevs.artiko.ui.LoadStateContent
import com.onlydevs.artiko.ui.artikoViewModel

@Composable
fun ArtViewScreen(workId: String, onExit: () -> Unit, onOpenArtist: (String) -> Unit) {
    val viewModel = artikoViewModel { api -> ArtViewViewModel(api, workId) }
    val state by viewModel.state.collectAsStateWithLifecycle()
    LoadStateContent(state = state, onRetry = viewModel::reload) { work ->
        ArtViewPage(work, onExit, onOpenArtist)
    }
}

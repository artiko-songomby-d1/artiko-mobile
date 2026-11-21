package com.onlydevs.artiko.feature.exposition

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.onlydevs.artiko.ui.LoadStateContent
import com.onlydevs.artiko.ui.artikoViewModel

@Composable
fun ExpositionScreen(expositionId: String, onBack: () -> Unit, onOpenArtView: (String) -> Unit) {
    val viewModel = artikoViewModel { api -> ExpositionViewModel(api, expositionId) }
    val state by viewModel.state.collectAsStateWithLifecycle()
    LoadStateContent(state = state, onRetry = viewModel::reload) { content ->
        ExpositionPage(content, onOpenArtView, onBack)
    }
}

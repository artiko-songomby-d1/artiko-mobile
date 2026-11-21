package com.onlydevs.artiko.feature.halloffame

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.onlydevs.artiko.ui.LoadStateContent
import com.onlydevs.artiko.ui.artikoViewModel

@Composable
fun HallOfFameScreen(onOpenArtView: (String) -> Unit) {
    val viewModel = artikoViewModel { api -> HallOfFameViewModel(api) }
    val state by viewModel.state.collectAsStateWithLifecycle()
    LoadStateContent(state = state, onRetry = viewModel::reload) { halls ->
        HallOfFamePage(halls, onOpenArtView)
    }
}

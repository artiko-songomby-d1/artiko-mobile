package com.onlydevs.artiko.feature.exposition

import com.onlydevs.artiko.data.ArtikoApi
import com.onlydevs.artiko.ui.LoadingViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class ExpositionViewModel(api: ArtikoApi, expositionId: String) :
    LoadingViewModel<ExpositionContent>({ loadExposition(api, expositionId) })

private suspend fun loadExposition(api: ArtikoApi, expositionId: String): ExpositionContent = coroutineScope {
    val exposition = async { api.getExposition(expositionId) }
    val works = async { api.listWorks(expositionId = expositionId) }
    ExpositionContent(exposition.await(), works.await())
}

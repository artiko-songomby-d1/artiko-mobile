package com.onlydevs.artiko.feature.discovery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onlydevs.artiko.data.ArtikoApi
import com.onlydevs.artiko.ui.LoadState
import com.onlydevs.artiko.ui.loadState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

private const val TYPING_PAUSE_MILLIS = 300L

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class DiscoveryViewModel(private val api: ArtikoApi) : ViewModel() {
    private val mutableQuery = MutableStateFlow("")
    private val retries = MutableStateFlow(0)
    private var shown: DiscoveryContent? = null

    val query: StateFlow<String> = mutableQuery.asStateFlow()

    /** Keeps the previous results on screen while a new search loads, so typing never loses focus. */
    val state: StateFlow<LoadState<DiscoveryContent>> = combine(
        mutableQuery.debounce { if (it.isEmpty()) 0L else TYPING_PAUSE_MILLIS },
        retries,
    ) { query, _ -> query.trim() }
        .flatMapLatest { query ->
            flow {
                if (shown == null) emit(LoadState.Loading)
                val result = loadState { loadDiscovery(api, query) }
                if (result is LoadState.Loaded) shown = result.data
                emit(result)
            }
        }
        .stateIn(viewModelScope, SharingStarted.Eagerly, LoadState.Loading)

    fun search(query: String) {
        mutableQuery.value = query
    }

    fun reload() {
        retries.value++
    }
}

private suspend fun loadDiscovery(api: ArtikoApi, query: String): DiscoveryContent = coroutineScope {
    val expositions = async { api.listExpositions() }
    val works = async { api.listWorks(query = query.ifEmpty { null }) }
    DiscoveryContent(expositions.await(), works.await())
}

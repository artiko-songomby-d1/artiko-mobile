package com.onlydevs.artiko.feature.discovery

import com.onlydevs.artiko.data.ArtikoApi
import com.onlydevs.artiko.ui.LoadingViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class DiscoveryViewModel(api: ArtikoApi) : LoadingViewModel<DiscoveryContent>({ loadDiscovery(api) })

private suspend fun loadDiscovery(api: ArtikoApi): DiscoveryContent = coroutineScope {
    val expositions = async { api.listExpositions() }
    val works = async { api.listWorks() }
    DiscoveryContent(expositions.await(), works.await())
}

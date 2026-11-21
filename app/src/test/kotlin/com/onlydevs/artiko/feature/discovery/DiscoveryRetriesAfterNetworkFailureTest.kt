package com.onlydevs.artiko.feature.discovery

import com.onlydevs.artiko.support.FakeArtikoApi
import com.onlydevs.artiko.support.MainDispatcherRule
import com.onlydevs.artiko.ui.LoadState
import java.io.IOException
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class DiscoveryRetriesAfterNetworkFailureTest {
    @get:Rule
    val mainDispatcher = MainDispatcherRule()

    @Test
    fun reloadLoadsTheFeedOnceTheNetworkIsBack() {
        val api = FakeArtikoApi(failure = IOException("offline"))
        val viewModel = DiscoveryViewModel(api)
        assertEquals(LoadState.Failed("offline"), viewModel.state.value)

        api.failure = null
        viewModel.reload()

        assertEquals(LoadState.Loaded(DiscoveryContent(emptyList(), emptyList())), viewModel.state.value)
    }
}

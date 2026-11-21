package com.onlydevs.artiko.feature.loading

import com.onlydevs.artiko.support.MainDispatcherRule
import com.onlydevs.artiko.ui.LoadState
import com.onlydevs.artiko.ui.LoadingViewModel
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class FailedLoadCanBeRetriedTest {
    @get:Rule
    val mainDispatcher = MainDispatcherRule()

    @Test
    fun reloadReplacesTheFailureWithTheLoadedData() {
        var offline = true
        val viewModel = LoadingViewModel { if (offline) error("offline") else "data" }
        assertEquals(LoadState.Failed("offline"), viewModel.state.value)

        offline = false
        viewModel.reload()

        assertEquals(LoadState.Loaded("data"), viewModel.state.value)
    }
}

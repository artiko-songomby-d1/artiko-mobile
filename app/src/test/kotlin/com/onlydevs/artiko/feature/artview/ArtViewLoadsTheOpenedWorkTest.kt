package com.onlydevs.artiko.feature.artview

import com.onlydevs.artiko.support.FakeArtikoApi
import com.onlydevs.artiko.support.MainDispatcherRule
import com.onlydevs.artiko.support.sampleWork
import com.onlydevs.artiko.ui.LoadState
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class ArtViewLoadsTheOpenedWorkTest {
    @get:Rule
    val mainDispatcher = MainDispatcherRule()

    @Test
    fun showsTheWorkWithTheRequestedId() {
        val opened = sampleWork(id = "opened")
        val api = FakeArtikoApi(works = listOf(sampleWork(id = "other"), opened))

        val viewModel = ArtViewViewModel(api, "opened")

        assertEquals(LoadState.Loaded(opened), viewModel.state.value)
    }
}

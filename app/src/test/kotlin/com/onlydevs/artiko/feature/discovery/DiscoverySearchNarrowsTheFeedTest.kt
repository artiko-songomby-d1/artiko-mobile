package com.onlydevs.artiko.feature.discovery

import com.onlydevs.artiko.support.FakeArtikoApi
import com.onlydevs.artiko.support.MainDispatcherRule
import com.onlydevs.artiko.support.sampleWork
import com.onlydevs.artiko.ui.LoadState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DiscoverySearchNarrowsTheFeedTest {
    @get:Rule
    val mainDispatcher = MainDispatcherRule()

    private val terraces = sampleWork(id = "terraces", title = "Terraces at Noon")
    private val sunrise = sampleWork(id = "sunrise", title = "Sunrise")

    // Built on first use: the main dispatcher rule has to be active before a ViewModel starts.
    private val viewModel by lazy { DiscoveryViewModel(FakeArtikoApi(works = listOf(terraces, sunrise))) }

    private fun shownWorks() = (viewModel.state.value as LoadState.Loaded).data.works

    @Test
    fun keepsOnlyTheWorksThatMatchTheWords() {
        viewModel.search("terraces")
        mainDispatcher.dispatcher.scheduler.advanceUntilIdle()

        assertEquals(listOf(terraces), shownWorks())
    }

    @Test
    fun showsEveryWorkAgainWhenTheSearchIsCleared() {
        viewModel.search("terraces")
        mainDispatcher.dispatcher.scheduler.advanceUntilIdle()

        viewModel.search("")

        assertEquals(listOf(terraces, sunrise), shownWorks())
    }

    @Test
    fun waitsForAPauseInTheTypingBeforeAskingTheApi() {
        viewModel.search("t")
        viewModel.search("te")

        assertEquals(listOf(terraces, sunrise), shownWorks())
    }
}

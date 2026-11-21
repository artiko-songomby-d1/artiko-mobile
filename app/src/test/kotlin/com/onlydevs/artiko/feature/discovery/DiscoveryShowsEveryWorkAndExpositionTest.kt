package com.onlydevs.artiko.feature.discovery

import com.onlydevs.artiko.support.FakeArtikoApi
import com.onlydevs.artiko.support.MainDispatcherRule
import com.onlydevs.artiko.support.sampleExposition
import com.onlydevs.artiko.support.sampleWork
import com.onlydevs.artiko.ui.LoadState
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class DiscoveryShowsEveryWorkAndExpositionTest {
    @get:Rule
    val mainDispatcher = MainDispatcherRule()

    @Test
    fun combinesWorksFromAllArtistsWithAllExpositions() {
        val works = listOf(sampleWork(id = "a", artistId = "artist-1"), sampleWork(id = "b", artistId = "artist-2"))
        val expositions = listOf(sampleExposition("expo-1", "artist-1"), sampleExposition("expo-2", "artist-2"))

        val viewModel = DiscoveryViewModel(FakeArtikoApi(works = works, expositions = expositions))

        assertEquals(LoadState.Loaded(DiscoveryContent(expositions, works)), viewModel.state.value)
    }
}

package com.onlydevs.artiko.feature.exposition

import com.onlydevs.artiko.support.FakeArtikoApi
import com.onlydevs.artiko.support.MainDispatcherRule
import com.onlydevs.artiko.support.sampleExposition
import com.onlydevs.artiko.support.sampleWork
import com.onlydevs.artiko.ui.LoadState
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class ExpositionFeedShowsOnlyItsWorksTest {
    @get:Rule
    val mainDispatcher = MainDispatcherRule()

    @Test
    fun excludesWorksOfOtherExpositions() {
        val inside = sampleWork(id = "inside", expositionId = "expo-1")
        val outside = sampleWork(id = "outside", expositionId = "expo-2")
        val api = FakeArtikoApi(
            works = listOf(inside, outside),
            expositions = listOf(sampleExposition("expo-1"), sampleExposition("expo-2")),
        )

        val viewModel = ExpositionViewModel(api, "expo-1")

        val expected = ExpositionContent(sampleExposition("expo-1"), listOf(inside))
        assertEquals(LoadState.Loaded(expected), viewModel.state.value)
    }
}

package com.onlydevs.artiko.feature.halloffame

import com.onlydevs.artiko.data.HallOfFameEntry
import com.onlydevs.artiko.support.FakeArtikoApi
import com.onlydevs.artiko.support.MainDispatcherRule
import com.onlydevs.artiko.support.sampleCategory
import com.onlydevs.artiko.support.sampleWork
import com.onlydevs.artiko.ui.LoadState
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class HallOfFameGroupsTopWorksByCategoryTest {
    @get:Rule
    val mainDispatcher = MainDispatcherRule()

    @Test
    fun listsCategoriesThatHaveVotesAndSkipsTheOthers() {
        val entry = HallOfFameEntry(rank = 1, work = sampleWork(), votes = 3)
        val api = FakeArtikoApi(
            categories = listOf(sampleCategory("painting"), sampleCategory("music")),
            hallOfFame = mapOf("painting" to listOf(entry)),
        )

        val viewModel = HallOfFameViewModel(api, month = "2026-11")

        val expected = listOf(CategoryHall(sampleCategory("painting"), listOf(entry)))
        assertEquals(LoadState.Loaded(expected), viewModel.state.value)
    }
}

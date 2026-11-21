package com.onlydevs.artiko.feature.artist

import com.onlydevs.artiko.support.FakeArtikoApi
import com.onlydevs.artiko.support.MainDispatcherRule
import com.onlydevs.artiko.support.sampleArtist
import com.onlydevs.artiko.support.sampleExposition
import com.onlydevs.artiko.support.sampleWork
import com.onlydevs.artiko.ui.LoadState
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class ArtistPageShowsProfileExpositionsAndWorksTest {
    @get:Rule
    val mainDispatcher = MainDispatcherRule()

    @Test
    fun keepsOnlyTheContentOfThatArtist() {
        val own = sampleWork(id = "own", artistId = "artist-1")
        val api = FakeArtikoApi(
            works = listOf(own, sampleWork(id = "other", artistId = "artist-2")),
            artists = listOf(sampleArtist("artist-1"), sampleArtist("artist-2")),
            expositions = listOf(sampleExposition("expo-1", "artist-1"), sampleExposition("expo-2", "artist-2")),
        )

        val viewModel = ArtistViewModel(api, "artist-1")

        val expected = ArtistContent(
            artist = sampleArtist("artist-1"),
            expositions = listOf(sampleExposition("expo-1", "artist-1")),
            works = listOf(own),
        )
        assertEquals(LoadState.Loaded(expected), viewModel.state.value)
    }
}

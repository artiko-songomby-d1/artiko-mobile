package com.onlydevs.artiko.feature.artist

import com.onlydevs.artiko.data.ArtikoApi
import com.onlydevs.artiko.ui.LoadingViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class ArtistViewModel(api: ArtikoApi, artistId: String) :
    LoadingViewModel<ArtistContent>({ loadArtist(api, artistId) })

private suspend fun loadArtist(api: ArtikoApi, artistId: String): ArtistContent = coroutineScope {
    val artist = async { api.getArtist(artistId) }
    val expositions = async { api.listExpositions(artistId = artistId) }
    val works = async { api.listWorks(artistId = artistId) }
    ArtistContent(artist.await(), expositions.await(), works.await())
}

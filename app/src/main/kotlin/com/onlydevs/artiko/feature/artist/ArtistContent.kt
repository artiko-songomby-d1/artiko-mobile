package com.onlydevs.artiko.feature.artist

import com.onlydevs.artiko.data.Artist
import com.onlydevs.artiko.data.Exposition
import com.onlydevs.artiko.data.Work

data class ArtistContent(val artist: Artist, val expositions: List<Exposition>, val works: List<Work>)

package com.onlydevs.artiko.support

import com.onlydevs.artiko.data.Artist
import com.onlydevs.artiko.data.Category
import com.onlydevs.artiko.data.Exposition
import com.onlydevs.artiko.data.MediaKind
import com.onlydevs.artiko.data.Work

fun sampleWork(
    id: String = "work-1",
    title: String = "Sunrise",
    artistId: String = "artist-1",
    category: String = "painting",
    expositionId: String? = null,
    mediaKind: MediaKind = MediaKind.Image,
) = Work(
    id = id,
    artistId = artistId,
    artistName = "Rado",
    title = title,
    story = "Painted at dawn",
    category = category,
    mediaUrl = "",
    mediaKind = mediaKind,
    humanProven = true,
    votes = 3,
    expositionId = expositionId,
)

fun sampleArtist(id: String = "artist-1") = Artist(id = id, displayName = "Rado", bio = "Weaver", region = "Antsirabe")

fun sampleExposition(id: String = "expo-1", artistId: String = "artist-1") = Exposition(
    id = id,
    artistId = artistId,
    artistName = "Rado",
    title = "Highlands",
    description = "Landscapes of the highlands",
    worksCount = 2,
)

fun sampleCategory(id: String = "painting") = Category(id = id, label = "Painting")

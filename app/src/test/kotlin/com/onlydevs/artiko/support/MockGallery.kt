package com.onlydevs.artiko.support

import com.onlydevs.artiko.data.Artist
import com.onlydevs.artiko.data.Category
import com.onlydevs.artiko.data.Exposition
import com.onlydevs.artiko.data.HallOfFameEntry
import com.onlydevs.artiko.data.MediaKind
import com.onlydevs.artiko.data.Work
import com.onlydevs.artiko.feature.halloffame.CategoryHall

/** A made-up Malagasy gallery, so the preview screenshots look like the real app. */
object MockGallery {
    val rado = Artist(
        id = "artist-rado",
        displayName = "Rado Andria",
        bio = "Weaves silk lamba in Ambositra and dyes them with indigo and annatto.",
        region = "Ambositra",
    )
    val miora = Artist(
        id = "artist-miora",
        displayName = "Miora Rakoto",
        bio = "Kabary orator carrying the proverbs of Imerina to a new generation.",
        region = "Antananarivo",
    )
    val tojo = Artist(
        id = "artist-tojo",
        displayName = "Tojo Razafy",
        bio = "Cuts bamboo at dawn to build valiha, and tunes each one by ear.",
        region = "Fianarantsoa",
    )
    val fara = Artist(
        id = "artist-fara",
        displayName = "Fara Ravelo",
        bio = "Paints the light on rice terraces and the red earth of the highlands.",
        region = "Antsirabe",
    )

    val threads = exposition("expo-threads", rado, "Threads of the Highlands", "Silk, indigo and patience.", 3)
    val voices = exposition("expo-voices", miora, "Voices of Imerina", "Speeches that outlive the speaker.", 2)
    val bamboo = exposition("expo-bamboo", tojo, "Bamboo Strings", "From the grove to the concert hall.", 2)
    val light = exposition("expo-light", fara, "Last Light", "Terraces at the hour before dusk.", 3)
    val expositions = listOf(threads, voices, bamboo, light)

    val indigoDusk = work("w1", rado, threads, "Indigo at Dusk", "crafts", MediaKind.Image, 38)
    val annattoWeave = work("w2", rado, threads, "Annatto Weave No. 3", "crafts", MediaKind.Image, 21)
    val zebuGate = work("w3", rado, threads, "Carved Zebu Gate", "sculpture", MediaKind.Model, 17)
    val hasina = work("w4", miora, voices, "Hasina, a Speech for the Ancestors", "oral_arts", MediaKind.Audio, 58)
    val harvest = work("w5", miora, voices, "Proverbs for the Rice Harvest", "oral_arts", MediaKind.Audio, 44)
    val bambooBreathes = work("w6", tojo, bamboo, "Bamboo Breathes", "music", MediaKind.Audio, 36)
    val tuning = work("w7", tojo, bamboo, "Tuning the Valiha", "music", MediaKind.Video, 29)
    val terraces = work("w8", fara, light, "Terraces at Noon", "painting", MediaKind.Image, 42)
    val redEarth = work("w9", fara, light, "Red Earth Study", "painting", MediaKind.Image, 31)
    val goldenHour = work("w10", fara, light, "Golden Hour over Antsirabe", "painting", MediaKind.Image, 25)
    val works = listOf(
        terraces,
        hasina,
        indigoDusk,
        tuning,
        redEarth,
        zebuGate,
        harvest,
        annattoWeave,
        goldenHour,
        bambooBreathes,
    )

    val hallOfFame = listOf(
        CategoryHall(Category("painting", "Painting"), ranked(terraces, redEarth, goldenHour)),
        CategoryHall(Category("oral_arts", "Oral arts"), ranked(hasina, harvest)),
        CategoryHall(Category("music", "Music"), ranked(bambooBreathes, tuning)),
    )

    private fun exposition(id: String, artist: Artist, title: String, description: String, worksCount: Int) =
        Exposition(id, artist.id, artist.displayName, title, description, coverUrl = null, worksCount = worksCount)

    private fun work(
        id: String,
        artist: Artist,
        exposition: Exposition,
        title: String,
        category: String,
        mediaKind: MediaKind,
        votes: Int,
    ) = Work(
        id = id,
        artistId = artist.id,
        artistName = artist.displayName,
        title = title,
        story = "$title, by ${artist.displayName}.",
        category = category,
        mediaUrl = "",
        mediaKind = mediaKind,
        humanProven = true,
        votes = votes,
        expositionId = exposition.id,
    )

    private fun ranked(vararg podium: Work) = podium.mapIndexed { index, work ->
        HallOfFameEntry(rank = index + 1, work = work, votes = work.votes)
    }

    fun worksOf(artist: Artist) = works.filter { it.artistId == artist.id }

    fun worksIn(exposition: Exposition) = works.filter { it.expositionId == exposition.id }

    fun expositionsOf(artist: Artist) = expositions.filter { it.artistId == artist.id }
}

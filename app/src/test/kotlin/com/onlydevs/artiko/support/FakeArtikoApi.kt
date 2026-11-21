package com.onlydevs.artiko.support

import com.onlydevs.artiko.data.ArtikoApi
import com.onlydevs.artiko.data.Artist
import com.onlydevs.artiko.data.Category
import com.onlydevs.artiko.data.Exposition
import com.onlydevs.artiko.data.HallOfFameEntry
import com.onlydevs.artiko.data.ScanResult
import com.onlydevs.artiko.data.Work
import okhttp3.MultipartBody

class FakeArtikoApi(
    var works: List<Work> = emptyList(),
    var artists: List<Artist> = emptyList(),
    var expositions: List<Exposition> = emptyList(),
    var categories: List<Category> = emptyList(),
    var hallOfFame: Map<String, List<HallOfFameEntry>> = emptyMap(),
    var scanResult: ScanResult? = null,
    var published: Work? = null,
    var failure: Exception? = null,
) : ArtikoApi {
    override suspend fun listWorks(
        category: String?,
        month: String?,
        artistId: String?,
        expositionId: String?,
    ): List<Work> = guarded {
        works.filter {
            (category == null || it.category == category) &&
                (artistId == null || it.artistId == artistId) &&
                (expositionId == null || it.expositionId == expositionId)
        }
    }

    override suspend fun getWork(id: String): Work = guarded { works.first { it.id == id } }

    override suspend fun getArtist(id: String): Artist = guarded { artists.first { it.id == id } }

    override suspend fun listExpositions(artistId: String?): List<Exposition> = guarded {
        expositions.filter { artistId == null || it.artistId == artistId }
    }

    override suspend fun getExposition(id: String): Exposition = guarded { expositions.first { it.id == id } }

    override suspend fun listCategories(): List<Category> = guarded { categories }

    override suspend fun getHallOfFame(category: String, month: String): List<HallOfFameEntry> = guarded {
        hallOfFame[category].orEmpty()
    }

    override suspend fun scanImage(file: MultipartBody.Part): ScanResult = guarded { requireNotNull(scanResult) }

    override suspend fun createWork(parts: List<MultipartBody.Part>): Work = guarded { requireNotNull(published) }

    private fun <T> guarded(block: () -> T): T {
        failure?.let { throw it }
        return block()
    }
}

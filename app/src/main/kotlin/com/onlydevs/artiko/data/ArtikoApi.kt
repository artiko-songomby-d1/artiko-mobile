package com.onlydevs.artiko.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArtikoApi {
    @GET("api/v1/works")
    suspend fun listWorks(
        @Query("category") category: String? = null,
        @Query("month") month: String? = null,
        @Query("artist_id") artistId: String? = null,
        @Query("exposition_id") expositionId: String? = null,
    ): List<Work>

    @GET("api/v1/works/{id}")
    suspend fun getWork(@Path("id") id: String): Work

    @GET("api/v1/artists/{id}")
    suspend fun getArtist(@Path("id") id: String): Artist

    @GET("api/v1/expositions")
    suspend fun listExpositions(@Query("artist_id") artistId: String? = null): List<Exposition>

    @GET("api/v1/expositions/{id}")
    suspend fun getExposition(@Path("id") id: String): Exposition

    @GET("api/v1/categories")
    suspend fun listCategories(): List<Category>

    @GET("api/v1/hall-of-fame/{category}/{month}")
    suspend fun getHallOfFame(@Path("category") category: String, @Path("month") month: String): List<HallOfFameEntry>
}

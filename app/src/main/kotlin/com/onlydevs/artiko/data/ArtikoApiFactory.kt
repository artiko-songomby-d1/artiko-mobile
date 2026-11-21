package com.onlydevs.artiko.data

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

private val json = Json { ignoreUnknownKeys = true }

fun createArtikoApi(baseUrl: String): ArtikoApi = Retrofit.Builder()
    .baseUrl(baseUrl)
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .build()
    .create(ArtikoApi::class.java)

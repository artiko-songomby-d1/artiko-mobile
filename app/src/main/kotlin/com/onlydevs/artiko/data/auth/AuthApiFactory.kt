package com.onlydevs.artiko.data.auth

import com.onlydevs.artiko.data.artikoJson
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

fun createAuthApi(baseUrl: String): AuthApi = Retrofit.Builder()
    .baseUrl(baseUrl)
    .addConverterFactory(artikoJson.asConverterFactory("application/json".toMediaType()))
    .build()
    .create(AuthApi::class.java)

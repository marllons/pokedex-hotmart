package com.debug.pokedex.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

class ServiceProvider {
    private val url = "https://pokeapi.co/api/"

    private val contentType = "application/json".toMediaType()
    private val json = Json {
        ignoreUnknownKeys = true
    }

    @OptIn(ExperimentalSerializationApi::class)
    private val retrofitBuilder =
        Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()

    fun <API> getService(api: Class<API>) = retrofitBuilder.create(api)
}
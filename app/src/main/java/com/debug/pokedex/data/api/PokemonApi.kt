package com.debug.pokedex.data.api

import com.debug.pokedex.data.model.PokemonBaseListRemote
import com.debug.pokedex.data.model.PokemonRemote
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {

    @GET("v2/pokemon/")
    suspend fun getPokemonList(
        @Query("offset") offset: Int?
    ): PokemonBaseListRemote

    @GET("v2/pokemon/{pokemonId}")
    suspend fun getPokemon(
        @Path("pokemonId") pokemonId: String,
    ): PokemonRemote
}
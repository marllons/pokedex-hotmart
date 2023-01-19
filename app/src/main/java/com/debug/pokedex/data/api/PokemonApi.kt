package com.debug.pokedex.data.api

import com.debug.pokedex.data.model.PokemonBaseListRemote
import com.debug.pokedex.data.model.PokemonRemote
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {

    @GET("v2/pokemon/")
    suspend fun getPokemonList(): PokemonBaseListRemote

    @GET("v2/pokemon/{pokemonId}")
    suspend fun getPokemon(
        @Path("pokemonId") pokemonId: String,
    ): PokemonRemote
}
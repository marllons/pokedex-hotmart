package com.debug.pokedex.data

import com.debug.pokedex.domain.entity.Pokemon
import com.debug.pokedex.domain.entity.PokemonBase

interface PokemonRepository {

    suspend fun getPokemonList(): List<PokemonBase>

    suspend fun getPokemon(id: String): Pokemon
}
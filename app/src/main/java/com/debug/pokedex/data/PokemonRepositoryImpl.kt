package com.debug.pokedex.data

import com.debug.pokedex.data.api.PokemonApi
import com.debug.pokedex.data.model.toPokemon
import com.debug.pokedex.data.model.toPokemonBase
import com.debug.pokedex.domain.entity.Pokemon
import com.debug.pokedex.domain.entity.PokemonBase
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonApi: PokemonApi
):PokemonRepository{

    override suspend fun getPokemonList(): List<PokemonBase> {
        return pokemonApi.getPokemonList().toPokemonBase()
    }

    override suspend fun getPokemon(id: String): Pokemon {
        return pokemonApi.getPokemon(id).toPokemon()
    }

}
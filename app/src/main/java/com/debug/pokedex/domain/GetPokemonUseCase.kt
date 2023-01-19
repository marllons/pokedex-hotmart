package com.debug.pokedex.domain

import com.debug.pokedex.data.PokemonRepository
import com.debug.pokedex.domain.entity.Pokemon
import javax.inject.Inject


class GetPokemonUseCase @Inject constructor(
    private val repository: PokemonRepository
) {

    suspend operator fun invoke(): List<Pokemon> {
        return repository.getPokemonList().map {
            val pokemonId: String = it.url.let { url ->
                url.substringAfter("https://pokeapi.co/api/v2/pokemon/").substringBefore('/')
            }
            repository.getPokemon(pokemonId)
        }
    }
}
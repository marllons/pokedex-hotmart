package com.debug.pokedex.domain

import com.debug.pokedex.data.PokemonRepository
import com.debug.pokedex.domain.entity.Pokemon
import javax.inject.Inject


class GetPokemonUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke() = repository.getPokemon()
}
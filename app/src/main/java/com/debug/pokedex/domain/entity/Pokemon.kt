package com.debug.pokedex.domain.entity

data class Pokemon(
    val id: Int,
    val name: String,
    val image: String,
    val height: Double,
    val weight: Double,
    val types: List<PokemonType>
)
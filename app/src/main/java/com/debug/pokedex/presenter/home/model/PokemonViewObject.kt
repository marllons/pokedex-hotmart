package com.debug.pokedex.presenter.home.model

import android.os.Parcelable
import com.debug.pokedex.domain.entity.Pokemon
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonViewObject(
    val id: Int,
    val name: String,
    val image: String,
    val height: Double,
    val weight: Double,
    val mainType: PokemonTypeResources,
    val types: List<PokemonTypeResources>
) : Parcelable {
    constructor(pokemon: Pokemon) : this(
        id = pokemon.id,
        name = pokemon.name,
        image = pokemon.image,
        height = pokemon.height,
        weight = pokemon.weight,
        mainType = PokemonTypeResources.fromPokemonType(pokemon.types.first()),
        types = pokemon.types.map { type ->
            PokemonTypeResources.fromPokemonType(type)
        },
    )
}
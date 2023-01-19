package com.debug.pokedex.data.model

import com.debug.pokedex.domain.entity.Pokemon

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

const val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/"

@Serializable
data class PokemonRemote(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("height") val height: Double,
    @SerialName("weight") val weight: Double,
    @SerialName("types") val types: List<PokemonTypeSlotRemote>,
)

fun PokemonRemote.toPokemon(): Pokemon {
    return Pokemon(
        id = id,
        name = name,
        image = "$imageUrl$id.png",
        height = height,
        weight = weight,
        types = types.toPokemonType()
    )
}
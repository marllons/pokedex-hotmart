package com.debug.pokedex.data.model

import com.debug.pokedex.domain.entity.PokemonBase
import com.debug.pokedex.domain.entity.PokemonType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonBaseListRemote(
    @SerialName("count") val count: Int,
    @SerialName("next") val next: String?,
    @SerialName("previous") val previous: String?,
    @SerialName("results") val results: List<PokemonBaseRemote>
)

fun PokemonBaseListRemote.toPokemonBase(): List<PokemonBase> {
    return results.map {
        PokemonBase(
            name = it.name,
            url = it.url
        )
    }
}

fun getType(type: String) = when (type) {
    "bug" -> PokemonType.BUG
    "dark" -> PokemonType.DARK
    "dragon" -> PokemonType.DRAGON
    "electric" -> PokemonType.ELECTRIC
    "fairy" -> PokemonType.FAIRLY
    "fighting" -> PokemonType.FIGHTING
    "fire" -> PokemonType.FIRE
    "flying" -> PokemonType.FLYING
    "ghost" -> PokemonType.GHOST
    "grass" -> PokemonType.GRASS
    "ground" -> PokemonType.GROUND
    "ice" -> PokemonType.ICE
    "normal" -> PokemonType.NORMAL
    "poison" -> PokemonType.POISON
    "psychic" -> PokemonType.PSYCHIC
    "rock" -> PokemonType.ROCK
    "steel" -> PokemonType.STEEL
    "water" -> PokemonType.WATER
    else -> PokemonType.NORMAL

}
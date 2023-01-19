package com.debug.pokedex.data.model

import com.debug.pokedex.domain.entity.PokemonType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonTypeRemote(
    @SerialName("name") val name: String,
    @SerialName("url") val url: String,
)


@Serializable
data class PokemonTypeSlotRemote(
    @SerialName("slot") val slot: Int,
    @SerialName("type") val pokemonType: PokemonTypeRemote,
)

fun List<PokemonTypeSlotRemote>.toPokemonType(): List<PokemonType> {
    return map {
        it.toPokemonType()
    }
}

fun PokemonTypeSlotRemote.toPokemonType(): PokemonType {
    return PokemonType.fromString(pokemonType.name)
}
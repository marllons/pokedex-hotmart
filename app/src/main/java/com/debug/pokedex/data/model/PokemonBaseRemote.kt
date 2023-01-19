package com.debug.pokedex.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonBaseRemote(
    @SerialName("name") val name: String,
    @SerialName("url") val url: String,
)

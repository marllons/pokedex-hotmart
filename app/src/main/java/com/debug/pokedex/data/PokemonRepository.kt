package com.debug.pokedex.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.debug.pokedex.domain.entity.Pokemon
import com.debug.pokedex.domain.entity.PokemonBase
import com.debug.pokedex.presenter.home.model.PokemonViewObject
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun getPokemon(): Flow<PagingData<PokemonViewObject>>
}
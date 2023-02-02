package com.debug.pokedex.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.debug.pokedex.Constants.PAGE_SIZE
import com.debug.pokedex.data.api.PokemonApi
import com.debug.pokedex.data.paging.PokedexPagingSource
import com.debug.pokedex.domain.entity.Pokemon
import com.debug.pokedex.presenter.home.model.PokemonViewObject
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonApi: PokemonApi
) : PokemonRepository {

    override suspend fun getPokemon(): Flow<PagingData<PokemonViewObject>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE
            ),
            pagingSourceFactory = { PokedexPagingSource(pokemonApi) }
        ).flow
    }
}
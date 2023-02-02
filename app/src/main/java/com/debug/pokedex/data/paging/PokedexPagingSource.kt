package com.debug.pokedex.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.debug.pokedex.Constants.LAST_OFFSET
import com.debug.pokedex.Constants.LAST_POSITION
import com.debug.pokedex.Constants.POKEMON_OFFSET
import com.debug.pokedex.Constants.POKEMON_STARTING_OFFSET
import com.debug.pokedex.data.api.PokemonApi
import com.debug.pokedex.data.model.toPokemon
import com.debug.pokedex.domain.entity.Pokemon
import com.debug.pokedex.presenter.home.model.PokemonViewObject
import java.io.IOException
import retrofit2.HttpException

class PokedexPagingSource(
    private val api: PokemonApi
) : PagingSource<Int, PokemonViewObject>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonViewObject> {
        val position = params.key ?: POKEMON_STARTING_OFFSET
        return try {
            val response = api.getPokemonList(
                if (position == LAST_POSITION) {
                    LAST_OFFSET
                } else {
                    position * POKEMON_OFFSET
                }
            )
            val pokemon = mutableListOf<PokemonViewObject>()
            response.results.map { result ->
                val singlePokemon = api.getPokemon(result.name)
                pokemon.add(PokemonViewObject(singlePokemon.toPokemon()))
            }
            LoadResult.Page(
                data = pokemon,
                prevKey = if (position == POKEMON_STARTING_OFFSET) null else position,
                nextKey = if (position == LAST_POSITION) null else position + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PokemonViewObject>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
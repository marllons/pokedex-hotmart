package com.debug.pokedex.presenter.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.debug.pokedex.domain.GetPokemonUseCase
import com.debug.pokedex.domain.entity.Pokemon
import com.debug.pokedex.presenter.home.model.PokemonListScreenState
import com.debug.pokedex.presenter.home.model.PokemonViewObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPokemon: GetPokemonUseCase
) : ViewModel() {

    private val _pokemons = MutableLiveData<Flow<PagingData<PokemonViewObject>>>()
    val pokemons: LiveData<Flow<PagingData<PokemonViewObject>>> = _pokemons

    fun getPokemonList() {
        viewModelScope.launch {
            _pokemons.postValue(getPokemon().cachedIn(viewModelScope))
        }
    }
}
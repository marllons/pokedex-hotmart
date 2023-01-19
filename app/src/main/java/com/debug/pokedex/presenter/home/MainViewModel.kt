package com.debug.pokedex.presenter.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.debug.pokedex.domain.GetPokemonUseCase
import com.debug.pokedex.presenter.home.model.PokemonListScreenState
import com.debug.pokedex.presenter.home.model.PokemonViewObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPokemon: GetPokemonUseCase
) : ViewModel() {

    private val _pokemons = MutableLiveData<List<PokemonViewObject>>()
    val pokemons: LiveData<List<PokemonViewObject>> = _pokemons

    private val _screenState = MutableLiveData<PokemonListScreenState>()
    val screenState: LiveData<PokemonListScreenState> = _screenState

    fun getPokemonList() {
        _screenState.value = PokemonListScreenState.LOADING

        viewModelScope.launch {
            _pokemons.postValue(
                getPokemon().map { pokemon ->
                    PokemonViewObject(pokemon)
                }
            )

            _screenState.postValue(PokemonListScreenState.CONTENT)
        }
    }
}
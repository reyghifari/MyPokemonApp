package com.hann.mypokemonapp.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hann.mypokemonapp.data.Resource
import com.hann.mypokemonapp.domain.usecase.PokemonUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainViewModel(
    private val useCase: PokemonUseCase
) : ViewModel() {

    private val _state = MutableLiveData<MainState>()
    val state : LiveData<MainState> = _state

    init {
        getPokemon()
    }

    private fun getPokemon() {
        useCase.getAllPokemon().onEach {
            result ->
            when(result){
                is Resource.Loading -> {
                    _state.value = MainState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = MainState(error = result.message ?: "An unexpected Error occured")
                }
                is Resource.Success -> {
                    _state.value = MainState(pokemon = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }

}
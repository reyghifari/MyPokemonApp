package com.hann.mypokemonapp.presentation.detail

import androidx.lifecycle.*
import com.hann.mypokemonapp.data.Resource
import com.hann.mypokemonapp.domain.usecase.PokemonUseCase
import com.hann.mypokemonapp.utils.Constants
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class DetailPokemonViewModel(
    private val useCase: PokemonUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableLiveData<DetailPokemonState>()
    val state : LiveData<DetailPokemonState> = _state


    init {
        savedStateHandle.get<String>(Constants.PARAM_POKEMON)?.let {
            getDetailPokemon(it)
        }
    }

    private fun getDetailPokemon(id: String) {
        useCase.getDetailPokemon(id).onEach {
                result ->
            when(result){
                is Resource.Loading -> {
                    _state.value = DetailPokemonState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = DetailPokemonState(error = result.message ?: "An unexpected Error occurred")
                }
                is Resource.Success -> {
                    _state.value = DetailPokemonState(pokemon = result.data)
                }

            }
        }.launchIn(viewModelScope)
    }


}
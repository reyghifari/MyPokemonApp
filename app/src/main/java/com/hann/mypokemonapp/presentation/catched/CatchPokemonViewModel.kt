package com.hann.mypokemonapp.presentation.catched

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.hann.mypokemonapp.domain.usecase.PokemonUseCase
import kotlinx.coroutines.flow.onEach

class CatchPokemonViewModel(
    private val useCase: PokemonUseCase
) : ViewModel() {

    val catchPokemon = useCase.getCatchPokemon().asLiveData()

}
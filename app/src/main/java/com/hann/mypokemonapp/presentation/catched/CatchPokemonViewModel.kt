package com.hann.mypokemonapp.presentation.catched

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.hann.mypokemonapp.core.domain.usecase.PokemonUseCase

class CatchPokemonViewModel(
    useCase: PokemonUseCase
) : ViewModel() {

    val catchPokemon = useCase.getCatchPokemon().asLiveData()

}
package com.hann.mypokemonapp.presentation.detail

import com.hann.mypokemonapp.domain.model.Pokemon

data class DetailPokemonState(
    val isLoading : Boolean = false,
    val pokemon: Pokemon? = null,
    val error: String = ""
)
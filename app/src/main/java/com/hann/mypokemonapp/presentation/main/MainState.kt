package com.hann.mypokemonapp.presentation.main

import com.hann.mypokemonapp.domain.model.Pokemon

data class MainState (
    val isLoading : Boolean = false,
    val pokemon: List<Pokemon> = emptyList(),
    val error: String = ""
)
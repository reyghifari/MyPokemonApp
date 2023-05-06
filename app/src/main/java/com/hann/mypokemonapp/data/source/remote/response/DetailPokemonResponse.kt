package com.hann.mypokemonapp.data.source.remote.response

data class DetailPokemonResponse(
    val name: String,
    val sprites: Sprites,
    val moves: List<Move>,
    val types: List<Type>,
    val weight: Int,
    val height: Int
)
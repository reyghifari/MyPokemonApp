package com.hann.mypokemonapp.domain.model


data class Pokemon(
    val id: String,
    val name: String,
    val moves: String?,
    val types: String?,
    val weight: Int?,
    val url: String,
    val height: Int?,
    val nickname : String?,
    val image: String?,
    val isCatch: Boolean
)
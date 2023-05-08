package com.hann.mypokemonapp.core.domain.model

import com.hann.mypokemonapp.core.data.source.remote.response.Move
import com.hann.mypokemonapp.core.data.source.remote.response.Type

data class Pokemon(
    val id: String,
    val name: String,
    val moves: List<Move>?,
    val types: List<Type>?,
    val weight: Int?,
    val url: String,
    val height: Int?,
    val nickname : String?,
    val image: String?,
    val isCatch: Boolean
)
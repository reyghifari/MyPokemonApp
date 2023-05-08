package com.hann.mypokemonapp.domain.usecase

import com.hann.mypokemonapp.data.Resource
import com.hann.mypokemonapp.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonUseCase {
    fun getAllPokemon(): Flow<Resource<List<Pokemon>>>

    fun getDetailPokemon(id: String) : Flow<Resource<Pokemon>>

    fun getCatchPokemon(): Flow<List<Pokemon>>
}
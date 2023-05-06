package com.hann.mypokemonapp.domain.repository

import com.hann.mypokemonapp.data.Resource
import com.hann.mypokemonapp.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface IPokemonRepository {

    fun getAllPokemon(): Flow<Resource<List<Pokemon>>>

}
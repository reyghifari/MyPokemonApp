package com.hann.mypokemonapp.core.domain.repository


import com.hann.mypokemonapp.core.data.Resource
import com.hann.mypokemonapp.core.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface IPokemonRepository {

    fun getAllPokemon(): Flow<Resource<List<Pokemon>>>
    fun getDetailPokemon(id: String) : Flow<Resource<Pokemon>>
    fun getCatchPokemon(): Flow<List<Pokemon>>
    fun setCatchPokemon(pokemon: Pokemon, state: Boolean, nick: String)
}
package com.hann.mypokemonapp.data.source.local

import com.hann.mypokemonapp.data.source.local.dao.PokemonDao
import com.hann.mypokemonapp.data.source.local.entity.PokemonEntity
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val pokemonDao: PokemonDao) {

    fun getAllPokemon(): Flow<List<PokemonEntity>> = pokemonDao.getAllPokemon()
    suspend fun insertPokemon(pokemonList: List<PokemonEntity>) = pokemonDao.insertPokemon(pokemonList)

    fun setCatchPokemon(pokemon: PokemonEntity, newState: Boolean, nick: String) {
        pokemon.isCatch = newState
        pokemon.nickname = nick
        pokemonDao.updateCatchPokemon(pokemon)
    }
}
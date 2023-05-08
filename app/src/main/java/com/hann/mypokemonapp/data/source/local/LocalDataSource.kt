package com.hann.mypokemonapp.data.source.local

import com.hann.mypokemonapp.data.source.local.dao.PokemonDao
import com.hann.mypokemonapp.data.source.local.entity.PokemonEntity
import com.hann.mypokemonapp.data.source.remote.response.Move
import com.hann.mypokemonapp.data.source.remote.response.Type
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val pokemonDao: PokemonDao) {

    fun getAllPokemon(): Flow<List<PokemonEntity>> = pokemonDao.getAllPokemon()

    fun getDetailPokemon(id: String): Flow<PokemonEntity> = pokemonDao.getDetailPokemon(id)

    fun getCatchPokemon() : Flow<List<PokemonEntity>> = pokemonDao.getCatchPokemon()

    suspend fun updatePokemonDetail(id: String, moves: List<Move>?, types: List<Type>?, weight: Int?, height: Int?) =
        pokemonDao.updateDetailPokemon(id, moves, types, weight, height)

    suspend fun insertPokemon(pokemonList: List<PokemonEntity>) = pokemonDao.insertPokemon(pokemonList)

    fun setCatchPokemon(pokemon: PokemonEntity, newState: Boolean, nick: String) {
        pokemon.isCatch = newState
        pokemon.nickname = nick
        pokemonDao.updateCatchPokemon(pokemon)
    }
}
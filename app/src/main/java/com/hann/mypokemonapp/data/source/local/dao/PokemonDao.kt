package com.hann.mypokemonapp.data.source.local.dao

import androidx.room.*
import com.hann.mypokemonapp.data.source.local.entity.PokemonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemon")
    fun getAllPokemon(): Flow<List<PokemonEntity>>

    @Query("SELECT * FROM pokemon WHERE isCatch = 1")
    fun getCatchPokemon(): Flow<List<PokemonEntity>>

    @Query("SELECT * FROM pokemon WHERE id = :pokemonId")
    fun getDetailPokemon(pokemonId: String): Flow<PokemonEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(pokemon: List<PokemonEntity>)

    @Query("UPDATE pokemon SET move = :moves, " + " types = :types, " + " weight = :weight, " + " height = :height WHERE id = :id")
    suspend fun updateDetailPokemon(id:String, moves: String?,
                                    types: String?, weight: Int?, height: Int?)

    @Update
    fun updateCatchPokemon(pokemon: PokemonEntity)

}
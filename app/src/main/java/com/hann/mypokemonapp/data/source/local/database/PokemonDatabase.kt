package com.hann.mypokemonapp.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hann.mypokemonapp.data.source.local.dao.PokemonDao
import com.hann.mypokemonapp.data.source.local.entity.PokemonEntity


@Database(entities = [PokemonEntity::class], version = 1, exportSchema = false)
abstract class PokemonDatabase : RoomDatabase() {

    abstract fun pokemonDao() : PokemonDao

}
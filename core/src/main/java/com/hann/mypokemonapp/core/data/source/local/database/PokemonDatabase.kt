package com.hann.mypokemonapp.core.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hann.mypokemonapp.core.data.source.local.dao.PokemonDao
import com.hann.mypokemonapp.core.data.source.local.entity.PokemonEntity
import com.hann.mypokemonapp.core.utils.ListConverter


@Database(entities = [PokemonEntity::class], version = 1, exportSchema = false)
@TypeConverters(ListConverter::class)
abstract class PokemonDatabase : RoomDatabase() {

    abstract fun pokemonDao() : PokemonDao

}
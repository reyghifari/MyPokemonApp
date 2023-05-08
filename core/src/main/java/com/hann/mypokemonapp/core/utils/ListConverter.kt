package com.hann.mypokemonapp.core.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hann.mypokemonapp.core.data.source.remote.response.Move
import com.hann.mypokemonapp.core.data.source.remote.response.Type

class ListConverter {

    @TypeConverter
    fun fromMovesList(moves: List<Move>): String {
        // Convert the List<Move> to a JSON string using Gson library
        return Gson().toJson(moves)
    }

    @TypeConverter
    fun toMovesList(movesString: String): List<Move> {
        // Convert the JSON string to List<Move> using Gson library
        val movesType = object : TypeToken<List<Move>>() {}.type
        return Gson().fromJson(movesString, movesType)
    }

    @TypeConverter
    fun fromTypesList(types: List<Type>): String {
        // Convert the List<Type> to a JSON string using Gson library
        return Gson().toJson(types)
    }

    @TypeConverter
    fun toTypesList(typesString: String): List<Type> {
        // Convert the JSON string to List<Type> using Gson library
        val typesType = object : TypeToken<List<Type>>() {}.type
        return Gson().fromJson(typesString, typesType)
    }

}
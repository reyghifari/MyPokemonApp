package com.hann.mypokemonapp.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.hann.mypokemonapp.core.data.source.remote.response.Move
import com.hann.mypokemonapp.core.data.source.remote.response.Type
import com.hann.mypokemonapp.core.utils.ListConverter

@Entity(tableName = "pokemon")
data class PokemonEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "imageDefault")
    var image: String,

    @ColumnInfo(name = "moves")
    @TypeConverters(ListConverter::class)
    val moves: List<Move>,

    @ColumnInfo(name = "types")
    @TypeConverters(ListConverter::class)
    val types: List<Type>,

    @ColumnInfo(name = "weight")
    val weight: Int?,

    @ColumnInfo(name = "url")
    val url: String,

    @ColumnInfo(name = "height")
    val height: Int?,

    @ColumnInfo(name = "nickname")
    var nickname: String = "",

    @ColumnInfo(name = "isCatch")
    var isCatch: Boolean = false
)
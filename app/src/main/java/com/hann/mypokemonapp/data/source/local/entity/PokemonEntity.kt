package com.hann.mypokemonapp.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hann.mypokemonapp.data.source.remote.response.Type

@Entity(tableName = "pokemon")
data class PokemonEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "imageDefault")
    var image: String,

    @ColumnInfo(name = "move")
    val move: String?,

    @ColumnInfo(name = "types")
    val types: String?,

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
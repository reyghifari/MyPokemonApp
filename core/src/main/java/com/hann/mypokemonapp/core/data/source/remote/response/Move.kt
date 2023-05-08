package com.hann.mypokemonapp.core.data.source.remote.response

data class Move(
    val move: MoveX,
    val version_group_details: List<VersionGroupDetail>
)
package com.hann.mypokemonapp.utils

import com.hann.mypokemonapp.data.source.local.entity.PokemonEntity
import com.hann.mypokemonapp.data.source.remote.response.PokemonResponse
import com.hann.mypokemonapp.domain.model.Pokemon

object Mapper {

    fun dataResponseToEntities(response: List<PokemonResponse>) : List<PokemonEntity>{
        val pokemonList = ArrayList<PokemonEntity>()
        response.map {

            val pokemonUrl =  it.url.replaceFirst(".$".toRegex(), "")
            val pokemonId = pokemonUrl.split('/').last()
            val pokemonImageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$pokemonId.png"
            val pokemon = PokemonEntity(
                id = pokemonId,
                name = it.name,
                height = null,
                weight = null,
                nickname = "",
                url = it.url,
                move = null,
                types = null,
                image = pokemonImageUrl,
                isCatch = false
            )
            pokemonList.add(pokemon)
        }
        return pokemonList
    }

    fun dataEntitiesToDomain(input: List<PokemonEntity>): List<Pokemon> =
        input.map {
            Pokemon(
                id = it.id,
                name = it.name,
                url = it.url,
                weight = it.weight,
                image = it.image,
                types = it.types,
                moves = it.move,
                nickname = it.nickname,
                height = it.height,
                isCatch = it.isCatch
            )
        }

    fun dataEntityToDomain(input: PokemonEntity): Pokemon =
        Pokemon(
            id = input.id,
            name = input.name,
            url = input.url,
            weight = input.weight,
            image = input.image,
            types = input.types,
            moves = input.move,
            nickname = input.nickname,
            height = input.height,
            isCatch = input.isCatch
        )

    fun dataDomainToEntity(input: Pokemon) = PokemonEntity(
        id = input.id,
        name = input.name,
        url = input.url,
        height = input.height,
        weight = input.weight,
        isCatch = input.isCatch,
        nickname = input.nickname ?: "",
        types = input.types,
        image = input.image ?: "",
        move = input.moves
    )

}
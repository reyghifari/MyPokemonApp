package com.hann.mypokemonapp.data

import com.hann.mypokemonapp.data.source.local.LocalDataSource
import com.hann.mypokemonapp.data.source.remote.RemoteDataSource
import com.hann.mypokemonapp.data.source.remote.network.ApiResponse
import com.hann.mypokemonapp.data.source.remote.response.PokemonResponse
import com.hann.mypokemonapp.domain.model.Pokemon
import com.hann.mypokemonapp.domain.repository.IPokemonRepository
import com.hann.mypokemonapp.utils.AppExecutors
import com.hann.mypokemonapp.utils.Mapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PokemonRepository(
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors,
    private val remoteDataSource: RemoteDataSource
) : IPokemonRepository{
    override fun getAllPokemon(): Flow<Resource<List<Pokemon>>> =
        object : NetworkBoundResource<List<Pokemon>, List<PokemonResponse>>() {
            override fun loadFromDB(): Flow<List<Pokemon>> {
                return localDataSource.getAllPokemon().map {
                    Mapper.dataEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Pokemon>?) = data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<PokemonResponse>>> =
                remoteDataSource.getAllPokemon()

            override suspend fun saveCallResult(data: List<PokemonResponse>) {
                val pokemonList = Mapper.dataResponseToEntities(data)
                localDataSource.insertPokemon(pokemonList)
            }
        }.asFlow()




}
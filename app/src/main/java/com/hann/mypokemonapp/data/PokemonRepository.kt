package com.hann.mypokemonapp.data

import com.hann.mypokemonapp.data.source.local.LocalDataSource
import com.hann.mypokemonapp.data.source.remote.RemoteDataSource
import com.hann.mypokemonapp.utils.AppExecutors

class PokemonRepository(
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors,
    private val remoteDataSource: RemoteDataSource
) {


}
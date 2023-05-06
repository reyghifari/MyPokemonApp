package com.hann.mypokemonapp.data.source.remote

import android.util.Log
import com.hann.mypokemonapp.data.source.remote.network.ApiResponse
import com.hann.mypokemonapp.data.source.remote.network.ApiService
import com.hann.mypokemonapp.data.source.remote.response.DetailPokemonResponse
import com.hann.mypokemonapp.data.source.remote.response.PokemonResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllPokemon(): Flow<ApiResponse<List<PokemonResponse>>> {
        return flow {
            try {
                val response = apiService.getAllPokemon()
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getPokemonDetail(id: String): Flow<ApiResponse<DetailPokemonResponse>> {
        return flow {
            try {
                val response = apiService.getPokemonDetail(id)
                emit(ApiResponse.Success(response))
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

}
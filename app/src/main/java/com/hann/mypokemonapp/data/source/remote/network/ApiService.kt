package com.hann.mypokemonapp.data.source.remote.network

import com.hann.mypokemonapp.data.source.remote.response.DetailPokemonResponse
import com.hann.mypokemonapp.data.source.remote.response.GetAllPokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("pokemon")
    suspend fun getAllPokemon(
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 20
    ): GetAllPokemonResponse

    @GET("pokemon/{id}")
    suspend fun getPokemonDetail(
        @Path("id") id: String?
    ): DetailPokemonResponse

}
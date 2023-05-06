package com.hann.mypokemonapp.data.source.remote.network

import com.hann.mypokemonapp.data.source.remote.response.GetAllPokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("pokemon")
    suspend fun getAllPokemon(): GetAllPokemonResponse
    
}
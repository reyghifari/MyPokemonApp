package com.hann.mypokemonapp.domain.usecase

import com.hann.mypokemonapp.data.Resource
import com.hann.mypokemonapp.domain.model.Pokemon
import com.hann.mypokemonapp.domain.repository.IPokemonRepository
import kotlinx.coroutines.flow.Flow

class PokemonInteractor(private val iPokemonRepository: IPokemonRepository) : PokemonUseCase {

    override fun getAllPokemon(): Flow<Resource<List<Pokemon>>> {
        return iPokemonRepository.getAllPokemon()
    }

    override fun getDetailPokemon(id: String): Flow<Resource<Pokemon>> {
        return iPokemonRepository.getDetailPokemon(id)
    }

    override fun getCatchPokemon(): Flow<List<Pokemon>> {
        return iPokemonRepository.getCatchPokemon()
    }

}
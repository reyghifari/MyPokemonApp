package com.hann.mypokemonapp.di

import com.hann.mypokemonapp.core.domain.usecase.PokemonInteractor
import com.hann.mypokemonapp.core.domain.usecase.PokemonUseCase
import com.hann.mypokemonapp.presentation.catched.CatchPokemonViewModel
import com.hann.mypokemonapp.presentation.detail.DetailPokemonViewModel
import com.hann.mypokemonapp.presentation.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<PokemonUseCase> { PokemonInteractor(get()) }
}

val viewModelModule = module {
    viewModel {
        MainViewModel(get())
    }
    viewModel {
        DetailPokemonViewModel(get(), get())
    }
    viewModel {
        CatchPokemonViewModel(get())
    }
}
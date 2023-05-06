package com.hann.mypokemonapp.di

import com.hann.mypokemonapp.presentation.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel {
        MainViewModel()
    }
}

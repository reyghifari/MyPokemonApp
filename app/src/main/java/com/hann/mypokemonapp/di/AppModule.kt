package com.hann.mypokemonapp.di

import androidx.room.Room
import com.hann.mypokemonapp.BuildConfig
import com.hann.mypokemonapp.data.PokemonRepository
import com.hann.mypokemonapp.data.source.local.LocalDataSource
import com.hann.mypokemonapp.data.source.local.database.PokemonDatabase
import com.hann.mypokemonapp.data.source.remote.RemoteDataSource
import com.hann.mypokemonapp.data.source.remote.network.ApiService
import com.hann.mypokemonapp.domain.repository.IPokemonRepository
import com.hann.mypokemonapp.domain.usecase.PokemonInteractor
import com.hann.mypokemonapp.domain.usecase.PokemonUseCase
import com.hann.mypokemonapp.presentation.detail.DetailPokemonViewModel
import com.hann.mypokemonapp.presentation.main.MainViewModel
import com.hann.mypokemonapp.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val databaseModule = module {
    factory {
        get<PokemonDatabase>().pokemonDao()
    }
    single {
        Room.databaseBuilder(
            androidContext(),
            PokemonDatabase::class.java, "pokemon.db"
        ).fallbackToDestructiveMigration()
            .build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(
                if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                } else {
                    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
                }
            )
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { RemoteDataSource(get()) }
    single { LocalDataSource(get()) }
    factory { AppExecutors() }
    single<IPokemonRepository> { PokemonRepository(get(),get(), get()) }
}


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
}

package com.hann.mypokemonapp.core.di

import androidx.room.Room
import com.hann.mypokemonapp.core.BuildConfig
import com.hann.mypokemonapp.core.data.PokemonRepository
import com.hann.mypokemonapp.core.data.source.local.LocalDataSource
import com.hann.mypokemonapp.core.data.source.local.database.PokemonDatabase
import com.hann.mypokemonapp.core.data.source.remote.RemoteDataSource
import com.hann.mypokemonapp.core.data.source.remote.network.ApiService
import com.hann.mypokemonapp.core.domain.repository.IPokemonRepository
import com.hann.mypokemonapp.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
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
    single<IPokemonRepository> {
       PokemonRepository(
            get(),
            get(),
            get()
        )
    }
}

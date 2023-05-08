package com.hann.mypokemonapp

import android.app.Application
import com.hann.mypokemonapp.core.di.databaseModule
import com.hann.mypokemonapp.core.di.networkModule
import com.hann.mypokemonapp.core.di.repositoryModule
import com.hann.mypokemonapp.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}
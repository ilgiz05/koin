package com.example.rickandmorty_

import android.app.Application
import com.example.rickandmorty_.servicelocator.appDatabaseModule
import com.example.rickandmorty_.servicelocator.networkModule
import com.example.rickandmorty_.servicelocator.repositoriesModule
import com.example.rickandmorty_.servicelocator.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(networkModule, repositoriesModule, viewModelsModule, appDatabaseModule)
        }
    }
}
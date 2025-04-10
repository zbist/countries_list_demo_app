package com.example.myapplication

import android.app.Application
import com.example.myapplication.di.NetworkModule
import com.example.myapplication.di.RepositoryModule
import com.example.myapplication.di.UseCasesModule
import com.example.myapplication.di.ViewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                ViewModelsModule.viewModelsModule,
                NetworkModule.networkModule,
                RepositoryModule.repositoryModule,
                UseCasesModule.useCasesModule
            )
        }
    }
}
package com.example.myapplication.di

import com.example.myapplication.data.RemoteRepository
import com.example.myapplication.domain.Repository
import org.koin.dsl.module

object RepositoryModule {
    val repositoryModule = module {
        single<Repository> {
            RemoteRepository(restApi = get())
        }
    }
}
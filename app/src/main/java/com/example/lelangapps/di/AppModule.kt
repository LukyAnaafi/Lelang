package com.example.lelangapps.di

import com.example.lelangapps.data.source.local.LocalDataSource
import com.example.lelangapps.data.source.remote.RemoteDataSource
import com.example.lelangapps.data.source.remote.network.ApiConfig
import org.koin.dsl.module

val appModule = module {
    single { ApiConfig.provideApiService }

    single { RemoteDataSource(get()) }

    single { LocalDataSource() }
}
package com.example.lelangapps.di

import com.example.lelangapps.data.repository.AppRepository
import com.example.lelangapps.data.repository.ItemAuctionRepository
import org.koin.core.scope.get
import org.koin.dsl.module

val repositoryModule = module {
    single { AppRepository(get(),get()) }
    single { ItemAuctionRepository(get(),get()) }
}
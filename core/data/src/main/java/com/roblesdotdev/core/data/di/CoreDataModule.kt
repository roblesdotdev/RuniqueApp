package com.roblesdotdev.core.data.di

import com.roblesdotdev.core.data.networking.HttpClientFactory
import org.koin.dsl.module

val coreDataModule = module {
    single {
        HttpClientFactory().build()
    }
}
package com.roblesdotdev.run.location.di

import com.roblesdotdev.run.domain.LocationObserver
import com.roblesdotdev.run.location.AndroidLocationObserver
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val locationModule = module {
    singleOf(::AndroidLocationObserver).bind<LocationObserver>()
}
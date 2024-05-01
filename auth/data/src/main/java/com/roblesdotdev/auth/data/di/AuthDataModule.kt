package com.roblesdotdev.auth.data.di

import com.roblesdotdev.auth.data.EmailPatternValidator
import com.roblesdotdev.auth.domain.PatternValidator
import com.roblesdotdev.auth.domain.UserDataValidator
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val authDataModule = module {
    single<PatternValidator> {
        EmailPatternValidator()
    }
    singleOf(::UserDataValidator)
}
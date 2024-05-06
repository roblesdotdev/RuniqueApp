package com.roblesdotdev.run.presentation.di

import com.roblesdotdev.run.domain.RunningTracker
import com.roblesdotdev.run.presentation.active_run.ActiveRunViewModel
import com.roblesdotdev.run.presentation.run_overview.RunOverviewViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val runPresentationModule = module {
    viewModelOf(::RunOverviewViewModel)
    viewModelOf(::ActiveRunViewModel)
    singleOf(::RunningTracker)
}
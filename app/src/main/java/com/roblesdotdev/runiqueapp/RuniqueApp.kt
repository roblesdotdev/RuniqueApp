package com.roblesdotdev.runiqueapp

import android.app.Application
import com.roblesdotdev.auth.data.di.authDataModule
import com.roblesdotdev.auth.presentation.di.authViewModelModule
import com.roblesdotdev.core.data.di.coreDataModule
import com.roblesdotdev.run.location.di.locationModule
import com.roblesdotdev.run.presentation.di.runPresentationModule
import com.roblesdotdev.runiqueapp.di.appModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class RuniqueApp : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@RuniqueApp)
            modules(
                authDataModule,
                authViewModelModule,
                appModule,
                coreDataModule,
                runPresentationModule,
                locationModule,
            )
        }
    }

}
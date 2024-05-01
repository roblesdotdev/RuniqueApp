package com.roblesdotdev.runiqueapp

import android.app.Application
import com.roblesdotdev.auth.data.di.authDataModule
import com.roblesdotdev.auth.presentation.di.authViewModelModule
import com.roblesdotdev.runiqueapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class RuniqueApp : Application() {
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
            )
        }
    }

}
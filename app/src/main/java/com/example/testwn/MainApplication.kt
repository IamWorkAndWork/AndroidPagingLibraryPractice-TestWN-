package com.example.testwn

import android.app.Application
import com.example.testwn.di.networkModule
import com.example.testwn.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(listOf(viewModelModule, networkModule))
        }
    }
}
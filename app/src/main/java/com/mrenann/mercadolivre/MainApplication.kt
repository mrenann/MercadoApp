package com.mrenann.mercadolivre

import android.app.Application
import com.mrenann.mercadolivre.core.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            try {
                androidContext(applicationContext)
                modules(
                    networkModule
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }
}
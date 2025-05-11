package com.mrenann.mercadolivre

import android.app.Application
import android.util.Log
import com.mrenann.mercadolivre.core.di.networkModule
import com.mrenann.mercadolivre.searchScreen.di.searchModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.error.KoinApplicationAlreadyStartedException

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            try {
                androidContext(applicationContext)
                modules(
                    networkModule,
                    searchModule,
                )
            } catch (e: KoinApplicationAlreadyStartedException) {
                Log.e("Koin", "Koin already started", e)
            }
        }
    }
}

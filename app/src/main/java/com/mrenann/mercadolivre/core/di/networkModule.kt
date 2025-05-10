package com.mrenann.mercadolivre.core.di

import android.content.Context
import com.mrenann.mercadolivre.core.data.remote.MockApiService
import com.mrenann.mercadolivre.core.data.remote.MockApiServiceImpl
import org.koin.dsl.module

val networkModule = module {
    single<MockApiService> {
        MockApiServiceImpl(
            context = get<Context>(),
        )
    }
}
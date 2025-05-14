package com.mrenann.mercadolivre.homeScreen.di

import com.mrenann.mercadolivre.homeScreen.presentation.screenModel.HomeScreenModel
import org.koin.dsl.module

val homeModule =
    module {
        factory<HomeScreenModel> {
            HomeScreenModel()
        }

    }
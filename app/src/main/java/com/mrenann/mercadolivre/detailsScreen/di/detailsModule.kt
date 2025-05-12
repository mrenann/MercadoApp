package com.mrenann.mercadolivre.detailsScreen.di

import com.mrenann.mercadolivre.core.data.remote.MockApiService
import com.mrenann.mercadolivre.detailsScreen.data.repository.DetailsRepositoryImpl
import com.mrenann.mercadolivre.detailsScreen.data.source.DetailsDataSourceImpl
import com.mrenann.mercadolivre.detailsScreen.data.usecase.GetProductUseCaseImpl
import com.mrenann.mercadolivre.detailsScreen.domain.repository.DetailsRepository
import com.mrenann.mercadolivre.detailsScreen.domain.source.DetailsDataSource
import com.mrenann.mercadolivre.detailsScreen.domain.usecase.GetProductUseCase
import com.mrenann.mercadolivre.detailsScreen.presentation.screenModel.DetailsScreenModel
import org.koin.dsl.module

val detailsModule =
    module {
        single<DetailsDataSource> {
            DetailsDataSourceImpl(
                service = get<MockApiService>(),
            )
        }
        single<DetailsRepository> {
            DetailsRepositoryImpl(
                dataSource = get<DetailsDataSource>(),
            )
        }
        single<GetProductUseCase> {
            GetProductUseCaseImpl(
                repository = get<DetailsRepository>(),
            )
        }
        factory<DetailsScreenModel> {
            DetailsScreenModel(
                getProductUseCase = get<GetProductUseCase>(),
            )
        }

    }

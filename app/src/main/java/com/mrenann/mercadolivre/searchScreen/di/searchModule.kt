package com.mrenann.mercadolivre.searchScreen.di

import com.mrenann.mercadolivre.core.data.remote.MockApiService
import com.mrenann.mercadolivre.searchScreen.data.repository.SearchRepositoryImpl
import com.mrenann.mercadolivre.searchScreen.data.source.SearchDataSourceImpl
import com.mrenann.mercadolivre.searchScreen.domain.repository.SearchRepository
import com.mrenann.mercadolivre.searchScreen.domain.source.SearchDataSource
import com.mrenann.mercadolivre.searchScreen.presentation.screenModel.SearchScreenModel
import org.koin.dsl.module

val searchModule =
    module {
        single<SearchDataSource> {
            SearchDataSourceImpl(
                service = get<MockApiService>(),
            )
        }
        single<SearchRepository> {
            SearchRepositoryImpl(
                dataSource = get<SearchDataSource>(),
            )
        }
        factory<SearchScreenModel> {
            SearchScreenModel(
                searchRepository = get<SearchRepository>(),
            )
        }
    }

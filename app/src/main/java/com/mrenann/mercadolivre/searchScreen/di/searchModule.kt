package com.mrenann.mercadolivre.searchScreen.di

import com.mrenann.mercadolivre.core.data.local.dao.SearchDao
import com.mrenann.mercadolivre.core.data.remote.MockApiService
import com.mrenann.mercadolivre.searchScreen.data.repository.QueriesLocalRepositoryImpl
import com.mrenann.mercadolivre.searchScreen.data.repository.SearchRepositoryImpl
import com.mrenann.mercadolivre.searchScreen.data.source.QueriesLocalDataSourceImpl
import com.mrenann.mercadolivre.searchScreen.data.source.SearchDataSourceImpl
import com.mrenann.mercadolivre.searchScreen.domain.repository.QueriesLocalRepository
import com.mrenann.mercadolivre.searchScreen.domain.repository.SearchRepository
import com.mrenann.mercadolivre.searchScreen.domain.source.QueriesLocalDataSource
import com.mrenann.mercadolivre.searchScreen.domain.source.SearchDataSource
import com.mrenann.mercadolivre.searchScreen.presentation.screenModel.RecentsSearchersScreenModel
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

        single<QueriesLocalDataSource> {
            QueriesLocalDataSourceImpl(
                dao = get<SearchDao>(),
            )
        }

        single<QueriesLocalRepository> {
            QueriesLocalRepositoryImpl(
                dataSource = get<QueriesLocalDataSource>()
            )
        }

        factory<RecentsSearchersScreenModel> {
            RecentsSearchersScreenModel(
                repository = get<QueriesLocalRepository>(),
            )
        }
    }

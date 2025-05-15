package com.mrenann.mercadolivre.searchScreen.data.repository

import com.mrenann.mercadolivre.searchScreen.domain.repository.QueriesLocalRepository
import com.mrenann.mercadolivre.searchScreen.domain.source.QueriesLocalDataSource
import kotlinx.coroutines.flow.Flow

class QueriesLocalRepositoryImpl(
    private val dataSource: QueriesLocalDataSource
) : QueriesLocalRepository {
    override fun getSearches(): Flow<List<String>> {
        return dataSource.getSearches()
    }

    override suspend fun insertSearchQuery(query: String) {
        return dataSource.insertSearchQuery(query)
    }

}

package com.mrenann.mercadolivre.searchScreen.data.source

import com.mrenann.mercadolivre.core.data.local.dao.SearchDao
import com.mrenann.mercadolivre.core.data.local.entity.SearchEntity
import com.mrenann.mercadolivre.searchScreen.domain.source.QueriesLocalDataSource
import kotlinx.coroutines.flow.Flow

class QueriesLocalDataSourceImpl(
    private val dao: SearchDao
) : QueriesLocalDataSource {
    override fun getSearches(): Flow<List<String>> {
        return dao.getLastSearches()
    }

    override suspend fun insertSearchQuery(query: String) {
        dao.insertNewSearch(
            SearchEntity(
                query
            )
        )
    }

}
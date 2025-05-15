package com.mrenann.mercadolivre.searchScreen.domain.source

import kotlinx.coroutines.flow.Flow

interface QueriesLocalDataSource {
    fun getSearches(): Flow<List<String>>
    suspend fun insertSearchQuery(query: String)
}
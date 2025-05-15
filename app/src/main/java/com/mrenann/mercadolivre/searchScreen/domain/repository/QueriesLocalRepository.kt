package com.mrenann.mercadolivre.searchScreen.domain.repository

import kotlinx.coroutines.flow.Flow

interface QueriesLocalRepository {
    fun getSearches(): Flow<List<String>>
    suspend fun insertSearchQuery(query: String)
}
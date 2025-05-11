package com.mrenann.mercadolivre.searchScreen.data.repository

import com.mrenann.mercadolivre.core.data.remote.response.SearchQueryResponse
import com.mrenann.mercadolivre.core.utils.Resource
import com.mrenann.mercadolivre.searchScreen.domain.repository.SearchRepository
import com.mrenann.mercadolivre.searchScreen.domain.source.SearchDataSource
import kotlinx.coroutines.flow.Flow

class SearchRepositoryImpl(
    private val dataSource: SearchDataSource
) : SearchRepository {
    override fun searchProducts(query: String): Flow<Resource<SearchQueryResponse>> {
        return dataSource.search(query)
    }
}
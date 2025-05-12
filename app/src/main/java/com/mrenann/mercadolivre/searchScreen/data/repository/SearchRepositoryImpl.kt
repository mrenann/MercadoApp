package com.mrenann.mercadolivre.searchScreen.data.repository

import com.mrenann.mercadolivre.core.data.remote.mappers.toDomain
import com.mrenann.mercadolivre.core.domain.model.SearchResult
import com.mrenann.mercadolivre.core.utils.Resource
import com.mrenann.mercadolivre.searchScreen.domain.repository.SearchRepository
import com.mrenann.mercadolivre.searchScreen.domain.source.SearchDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SearchRepositoryImpl(
    private val dataSource: SearchDataSource
) : SearchRepository {
    override fun searchProducts(query: String): Flow<Resource<List<SearchResult>>> {
        return dataSource.search(query).map { resource ->
            resource.map { response ->
                (response.results ?: emptyList()).map { it.toDomain() }
            }
        }
    }
}
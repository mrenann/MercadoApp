package com.mrenann.mercadolivre.searchScreen.domain.repository

import com.mrenann.mercadolivre.core.data.remote.response.SearchQueryResponse
import com.mrenann.mercadolivre.core.utils.Resource
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun searchProducts(query: String): Flow<Resource<SearchQueryResponse>>
}
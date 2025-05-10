package com.mrenann.mercadolivre.core.data.remote

import com.mrenann.mercadolivre.core.data.remote.response.SearchQueryResponse
import kotlinx.coroutines.flow.Flow

interface MockApiService {
    fun searchProducts(query: String): Flow<SearchQueryResponse>
}

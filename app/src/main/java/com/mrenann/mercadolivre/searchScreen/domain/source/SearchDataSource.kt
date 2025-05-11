package com.mrenann.mercadolivre.searchScreen.domain.source

import com.mrenann.mercadolivre.core.data.remote.response.SearchQueryResponse
import com.mrenann.mercadolivre.core.utils.Resource
import kotlinx.coroutines.flow.Flow

interface SearchDataSource {
    fun search(query: String): Flow<Resource<SearchQueryResponse>>
}
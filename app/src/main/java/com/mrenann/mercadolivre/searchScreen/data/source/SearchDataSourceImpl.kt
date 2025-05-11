package com.mrenann.mercadolivre.searchScreen.data.source

import com.mrenann.mercadolivre.core.data.remote.MockApiService
import com.mrenann.mercadolivre.core.data.remote.response.SearchQueryResponse
import com.mrenann.mercadolivre.core.utils.Resource
import com.mrenann.mercadolivre.core.utils.logError
import com.mrenann.mercadolivre.core.utils.mapExceptionToErrorMessage
import com.mrenann.mercadolivre.searchScreen.domain.source.SearchDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchDataSourceImpl(
    private val service: MockApiService
) : SearchDataSource {
    override fun search(query: String): Flow<Resource<SearchQueryResponse>> = flow {
        emit(Resource.Loading)
        try {
            service.searchProducts(query)
                .collect { response ->
                    emit(Resource.Success(response))
                }
        } catch (e: Exception) {
            logError(e)
            emit(Resource.Error(mapExceptionToErrorMessage(e)))
        }
    }
}
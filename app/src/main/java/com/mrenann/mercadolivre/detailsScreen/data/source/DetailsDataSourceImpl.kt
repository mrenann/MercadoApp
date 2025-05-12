package com.mrenann.mercadolivre.detailsScreen.data.source

import com.mrenann.mercadolivre.core.data.remote.MockApiService
import com.mrenann.mercadolivre.core.data.remote.response.ItemCategoryResponse
import com.mrenann.mercadolivre.core.data.remote.response.ItemDescriptionResponse
import com.mrenann.mercadolivre.core.data.remote.response.ItemDetailsResponse
import com.mrenann.mercadolivre.core.utils.Resource
import com.mrenann.mercadolivre.core.utils.logError
import com.mrenann.mercadolivre.core.utils.mapExceptionToErrorMessage
import com.mrenann.mercadolivre.detailsScreen.domain.source.DetailsDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DetailsDataSourceImpl(
    private val service: MockApiService
) : DetailsDataSource {
    override fun getItem(id: String): Flow<Resource<ItemDetailsResponse>> = flow {
        emit(Resource.Loading)
        try {
            service.getItem(id)
                .collect { response ->
                    emit(Resource.Success(response))
                }
        } catch (e: Exception) {
            logError(e)
            emit(Resource.Error(mapExceptionToErrorMessage(e)))
        }
    }

    override fun getItemCategory(id: String): Flow<Resource<ItemCategoryResponse>> = flow {
        emit(Resource.Loading)
        try {
            service.getItemCategory(id)
                .collect { response ->
                    emit(Resource.Success(response))
                }
        } catch (e: Exception) {
            logError(e)
            emit(Resource.Error(mapExceptionToErrorMessage(e)))
        }
    }

    override fun getItemDescription(id: String): Flow<Resource<ItemDescriptionResponse>> = flow {
        emit(Resource.Loading)
        try {
            service.getItemDescription(id)
                .collect { response ->
                    emit(Resource.Success(response))
                }
        } catch (e: Exception) {
            logError(e)
            emit(Resource.Error(mapExceptionToErrorMessage(e)))
        }
    }
}
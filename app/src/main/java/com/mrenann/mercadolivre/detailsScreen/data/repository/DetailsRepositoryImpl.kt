package com.mrenann.mercadolivre.detailsScreen.data.repository

import com.mrenann.mercadolivre.core.data.remote.response.ItemCategoryResponse
import com.mrenann.mercadolivre.core.data.remote.response.ItemDescriptionResponse
import com.mrenann.mercadolivre.core.data.remote.response.ItemDetailsResponse
import com.mrenann.mercadolivre.core.utils.Resource
import com.mrenann.mercadolivre.detailsScreen.domain.repository.DetailsRepository
import com.mrenann.mercadolivre.detailsScreen.domain.source.DetailsDataSource
import kotlinx.coroutines.flow.Flow

class DetailsRepositoryImpl(
    private val dataSource: DetailsDataSource
) : DetailsRepository {
    override fun getItem(id: String): Flow<Resource<ItemDetailsResponse>> {
        return dataSource.getItem(id)
    }

    override fun getItemCategory(id: String): Flow<Resource<ItemCategoryResponse>> {
        return dataSource.getItemCategory(id)
    }

    override fun getItemDescription(id: String): Flow<Resource<ItemDescriptionResponse>> {
        return dataSource.getItemDescription(id)
    }
}
package com.mrenann.mercadolivre.detailsScreen.domain.repository

import com.mrenann.mercadolivre.core.data.remote.response.ItemCategoryResponse
import com.mrenann.mercadolivre.core.data.remote.response.ItemDescriptionResponse
import com.mrenann.mercadolivre.core.data.remote.response.ItemDetailsResponse
import com.mrenann.mercadolivre.core.utils.Resource
import kotlinx.coroutines.flow.Flow

interface DetailsRepository {
    fun getItem(id: String): Flow<Resource<ItemDetailsResponse>>
    fun getItemCategory(id: String): Flow<Resource<ItemCategoryResponse>>
    fun getItemDescription(id: String): Flow<Resource<ItemDescriptionResponse>>
}
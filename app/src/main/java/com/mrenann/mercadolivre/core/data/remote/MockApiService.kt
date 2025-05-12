package com.mrenann.mercadolivre.core.data.remote

import com.mrenann.mercadolivre.core.data.remote.response.ItemCategoryResponse
import com.mrenann.mercadolivre.core.data.remote.response.ItemDescriptionResponse
import com.mrenann.mercadolivre.core.data.remote.response.ItemDetailsResponse
import com.mrenann.mercadolivre.core.data.remote.response.SearchQueryResponse
import kotlinx.coroutines.flow.Flow

interface MockApiService {
    fun searchProducts(query: String): Flow<SearchQueryResponse>
    fun getItem(id: String): Flow<ItemDetailsResponse>
    fun getItemCategory(id: String): Flow<ItemCategoryResponse>
    fun getItemDescription(id: String): Flow<ItemDescriptionResponse>
}

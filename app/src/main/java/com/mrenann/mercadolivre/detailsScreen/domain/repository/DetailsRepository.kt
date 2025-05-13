package com.mrenann.mercadolivre.detailsScreen.domain.repository

import com.mrenann.mercadolivre.core.domain.model.Category
import com.mrenann.mercadolivre.core.domain.model.ProductDetails
import com.mrenann.mercadolivre.core.utils.Resource
import kotlinx.coroutines.flow.Flow

interface DetailsRepository {
    fun getItem(id: String): Flow<Resource<ProductDetails>>
    fun getItemCategory(id: String): Flow<Resource<Category>>
    fun getItemDescription(id: String): Flow<Resource<String>>
}
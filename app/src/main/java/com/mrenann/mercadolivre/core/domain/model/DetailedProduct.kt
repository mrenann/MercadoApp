package com.mrenann.mercadolivre.core.domain.model

import com.mrenann.mercadolivre.core.data.remote.response.ItemCategoryResponse
import com.mrenann.mercadolivre.core.data.remote.response.ItemDescriptionResponse
import com.mrenann.mercadolivre.core.data.remote.response.ItemDetailsResponse
import com.mrenann.mercadolivre.core.utils.Resource

data class DetailedProduct(
    val item: Resource<ItemDetailsResponse>,
    val category: Resource<ItemCategoryResponse>,
    val description: Resource<ItemDescriptionResponse>
)
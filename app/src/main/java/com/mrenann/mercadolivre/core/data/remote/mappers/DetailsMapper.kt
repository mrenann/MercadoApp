package com.mrenann.mercadolivre.core.data.remote.mappers

import com.mrenann.mercadolivre.core.data.remote.response.ItemCategoryResponse
import com.mrenann.mercadolivre.core.data.remote.response.ItemDescriptionResponse
import com.mrenann.mercadolivre.core.data.remote.response.ItemDetailsResponse
import com.mrenann.mercadolivre.core.domain.model.Category
import com.mrenann.mercadolivre.core.domain.model.PathFromRoot
import com.mrenann.mercadolivre.core.domain.model.ProductDetails
import com.mrenann.mercadolivre.core.utils.forceHttps


fun ItemDetailsResponse.toDomain(): ProductDetails {
    return ProductDetails(
        pictures = pictures?.map { (it.url ?: "").forceHttps() } ?: emptyList(),
        quantity = initialQuantity ?: 0,
        originalPrice = originalPrice,
        price = price ?: 0.0,
        currencyId = currencyId ?: "BRL",
        condition = condition ?: "new",
        title = title ?: ""
    )
}

fun ItemCategoryResponse.toDomain(): Category {
    return Category(
        id = id,
        name = name,
        picture = picture?.forceHttps(),
        totalItems = totalItemsInThisCategory,
        pathFromRoot = pathFromRoot?.map {
            PathFromRoot(
                id = it.id,
                name = it.name
            )
        }
    )
}

fun ItemDescriptionResponse.toDomain(): String {
    return plainText ?: ""
}

package com.mrenann.mercadolivre.core.domain.model

data class DetailedProduct(
    val item: ProductDetails?,
    val category: Category?,
    val description: String?,
    val errors: List<String>? = null
)
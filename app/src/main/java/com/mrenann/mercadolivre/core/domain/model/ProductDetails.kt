package com.mrenann.mercadolivre.core.domain.model

data class ProductDetails(
    val pictures: List<String> = emptyList(),
    val quantity: Int,
    val originalPrice: Double? = null,
    val price: Double = 0.0,
    val currencyId: String = "BRL",
    val condition: String = "",
    val title: String = "",
)
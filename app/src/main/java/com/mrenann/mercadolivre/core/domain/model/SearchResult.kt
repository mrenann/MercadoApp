package com.mrenann.mercadolivre.core.domain.model

import java.io.Serializable

data class SearchResult(
    val thumbnail: String? = "",
    val title: String? = "",
    val originalPrice: Double? = 0.0,
    val price: Double? = 0.0,
    val freeShipping: Boolean? = false,
    val currencyId: String? = null,
    val id: String? = null
) : Serializable
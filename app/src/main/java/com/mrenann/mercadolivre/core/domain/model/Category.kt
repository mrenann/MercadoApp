package com.mrenann.mercadolivre.core.domain.model

data class Category(
    val id: String? = "",
    val name: String? = "",
    val picture: String? = "",
    val totalItems: Int? = 0,
    val pathFromRoot: List<PathFromRoot>? = listOf(),
)

package com.mrenann.mercadolivre.core.data.remote.model

import com.google.gson.annotations.SerializedName

data class ProductInfo(
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("score")
    val score: Int? = 0,
    @SerializedName("status")
    val status: String? = "",
)

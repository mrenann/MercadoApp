package com.mrenann.mercadolivre.core.data.remote.model


import com.google.gson.annotations.SerializedName

data class PdpTracking(
    @SerializedName("group")
    val group: Boolean? = false,
    @SerializedName("product_info")
    val productInfo: List<ProductInfo?>? = listOf()
)
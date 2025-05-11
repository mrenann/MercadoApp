package com.mrenann.mercadolivre.core.data.remote.model.search

import com.google.gson.annotations.SerializedName

data class Shipping(
    @SerializedName("benefits")
    val benefits: Any? = Any(),
    @SerializedName("free_shipping")
    val freeShipping: Boolean? = false,
    @SerializedName("logistic_type")
    val logisticType: String? = "",
    @SerializedName("mode")
    val mode: String? = "",
    @SerializedName("promise")
    val promise: Any? = Any(),
    @SerializedName("shipping_score")
    val shippingScore: Int? = 0,
    @SerializedName("store_pick_up")
    val storePickUp: Boolean? = false,
    @SerializedName("tags")
    val tags: List<String?>? = listOf(),
)

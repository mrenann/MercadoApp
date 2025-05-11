package com.mrenann.mercadolivre.core.data.remote.model.details


import com.google.gson.annotations.SerializedName

data class Shipping(
    @SerializedName("dimensions")
    val dimensions: Any? = Any(),
    @SerializedName("free_shipping")
    val freeShipping: Boolean? = false,
    @SerializedName("local_pick_up")
    val localPickUp: Boolean? = false,
    @SerializedName("logistic_type")
    val logisticType: String? = "",
    @SerializedName("methods")
    val methods: List<Any?>? = listOf(),
    @SerializedName("mode")
    val mode: String? = "",
    @SerializedName("store_pick_up")
    val storePickUp: Boolean? = false,
    @SerializedName("tags")
    val tags: List<Any?>? = listOf()
)
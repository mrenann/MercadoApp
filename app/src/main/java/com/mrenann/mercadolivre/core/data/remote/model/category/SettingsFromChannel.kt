package com.mrenann.mercadolivre.core.data.remote.model.category


import com.google.gson.annotations.SerializedName

data class SettingsFromChannel(
    @SerializedName("buying_modes")
    val buyingModes: List<String?>? = listOf(),
    @SerializedName("immediate_payment")
    val immediatePayment: String? = "",
    @SerializedName("minimum_price")
    val minimumPrice: Int? = 0,
    @SerializedName("status")
    val status: String? = ""
)
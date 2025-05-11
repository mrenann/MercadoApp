package com.mrenann.mercadolivre.core.data.remote.model


import com.google.gson.annotations.SerializedName

data class Installments(
    @SerializedName("amount")
    val amount: Double? = 0.0,
    @SerializedName("currency_id")
    val currencyId: String? = "",
    @SerializedName("metadata")
    val metadata: Metadata? = Metadata(),
    @SerializedName("quantity")
    val quantity: Int? = 0,
    @SerializedName("rate")
    val rate: Double? = 0.0
)
package com.mrenann.mercadolivre.core.data.remote.model


import com.google.gson.annotations.SerializedName

data class SalePrice(
    @SerializedName("amount")
    val amount: Double? = 0.0,
    @SerializedName("conditions")
    val conditions: Conditions? = Conditions(),
    @SerializedName("currency_id")
    val currencyId: String? = "",
    @SerializedName("exchange_rate")
    val exchangeRate: Any? = Any(),
    @SerializedName("metadata")
    val metadata: MetadataPrice? = MetadataPrice(),
    @SerializedName("payment_method_prices")
    val paymentMethodPrices: List<Any?>? = listOf(),
    @SerializedName("payment_method_type")
    val paymentMethodType: String? = "",
    @SerializedName("price_id")
    val priceId: String? = "",
    @SerializedName("regular_amount")
    val regularAmount: Double? = 0.0,
    @SerializedName("type")
    val type: String? = ""
)
package com.mrenann.mercadolivre.core.data.remote.model.search

import com.google.gson.annotations.SerializedName

data class MetadataPrice(
    @SerializedName("campaign_discount_percentage")
    val campaignDiscountPercentage: Double? = 0.0,
    @SerializedName("campaign_end_date")
    val campaignEndDate: String? = "",
    @SerializedName("campaign_id")
    val campaignId: String? = "",
    @SerializedName("discount_meli_amount")
    val discountMeliAmount: Double? = 0.0,
    @SerializedName("experiment_id")
    val experimentId: String? = "",
    @SerializedName("funding_mode")
    val fundingMode: String? = "",
    @SerializedName("order_item_price")
    val orderItemPrice: Double? = 0.0,
    @SerializedName("promotion_id")
    val promotionId: String? = "",
    @SerializedName("promotion_offer_sub_type")
    val promotionOfferSubType: String? = "",
    @SerializedName("promotion_offer_type")
    val promotionOfferType: String? = "",
    @SerializedName("promotion_type")
    val promotionType: String? = "",
    @SerializedName("variation")
    val variation: String? = "",
)

package com.mrenann.mercadolivre.core.data.remote.model


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("accepts_mercadopago")
    val acceptsMercadopago: Boolean? = false,
    @SerializedName("address")
    val address: Address? = Address(),
    @SerializedName("attributes")
    val attributes: List<Attribute>? = listOf(),
    @SerializedName("available_quantity")
    val availableQuantity: Int? = 0,
    @SerializedName("buying_mode")
    val buyingMode: String? = "",
    @SerializedName("catalog_listing")
    val catalogListing: Boolean? = false,
    @SerializedName("catalog_product_id")
    val catalogProductId: String? = "",
    @SerializedName("category_id")
    val categoryId: String? = "",
    @SerializedName("condition")
    val condition: String? = "",
    @SerializedName("currency_id")
    val currencyId: String? = "",
    @SerializedName("discounts")
    val discounts: Any? = Any(),
    @SerializedName("domain_id")
    val domainId: String? = "",
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("installments")
    val installments: Installments? = Installments(),
    @SerializedName("installments_motors")
    val installmentsMotors: Any? = Any(),
    @SerializedName("inventory_id")
    val inventoryId: String? = "",
    @SerializedName("listing_type_id")
    val listingTypeId: String? = "",
    @SerializedName("official_store_id")
    val officialStoreId: Int? = 0,
    @SerializedName("official_store_name")
    val officialStoreName: String? = "",
    @SerializedName("order_backend")
    val orderBackend: Int? = 0,
    @SerializedName("original_price")
    val originalPrice: Double? = 0.0,
    @SerializedName("permalink")
    val permalink: String? = "",
    @SerializedName("price")
    val price: Double? = 0.0,
    @SerializedName("promotion_decorations")
    val promotionDecorations: Any? = Any(),
    @SerializedName("promotions")
    val promotions: Any? = Any(),
    @SerializedName("result_type")
    val resultType: String? = "",
    @SerializedName("sale_price")
    val salePrice: SalePrice? = SalePrice(),
    @SerializedName("sanitized_title")
    val sanitizedTitle: String? = "",
    @SerializedName("seller")
    val seller: Seller? = Seller(),
    @SerializedName("shipping")
    val shipping: Shipping? = Shipping(),
    @SerializedName("site_id")
    val siteId: String? = "",
    @SerializedName("stop_time")
    val stopTime: String? = "",
    @SerializedName("thumbnail")
    val thumbnail: String? = "",
    @SerializedName("thumbnail_id")
    val thumbnailId: String? = "",
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("use_thumbnail_id")
    val useThumbnailId: Boolean? = false,
    @SerializedName("winner_item_id")
    val winnerItemId: Any? = Any()
)
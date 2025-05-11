package com.mrenann.mercadolivre.core.data.remote.response


import com.google.gson.annotations.SerializedName
import com.mrenann.mercadolivre.core.data.remote.model.details.Attribute
import com.mrenann.mercadolivre.core.data.remote.model.details.Location
import com.mrenann.mercadolivre.core.data.remote.model.details.Picture
import com.mrenann.mercadolivre.core.data.remote.model.details.SaleTerm
import com.mrenann.mercadolivre.core.data.remote.model.details.SellerAddress
import com.mrenann.mercadolivre.core.data.remote.model.details.Shipping

data class ItemDetailsResponse(
    @SerializedName("accepts_mercadopago")
    val acceptsMercadopago: Boolean? = false,
    @SerializedName("attributes")
    val attributes: List<Attribute>? = listOf(),
    @SerializedName("automatic_relist")
    val automaticRelist: Boolean? = false,
    @SerializedName("base_price")
    val basePrice: Double? = 0.0,
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
    @SerializedName("coverage_areas")
    val coverageAreas: List<Any?>? = listOf(),
    @SerializedName("currency_id")
    val currencyId: String? = "",
    @SerializedName("date_created")
    val dateCreated: String? = "",
    @SerializedName("deal_ids")
    val dealIds: List<Any?>? = listOf(),
    @SerializedName("descriptions")
    val descriptions: List<Any?>? = listOf(),
    @SerializedName("domain_id")
    val domainId: String? = "",
    @SerializedName("health")
    val health: Any? = Any(),
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("initial_quantity")
    val initialQuantity: Int? = 0,
    @SerializedName("international_delivery_mode")
    val internationalDeliveryMode: String? = "",
    @SerializedName("last_updated")
    val lastUpdated: String? = "",
    @SerializedName("listing_source")
    val listingSource: String? = "",
    @SerializedName("listing_type_id")
    val listingTypeId: String? = "",
    @SerializedName("location")
    val location: Location? = Location(),
    @SerializedName("non_mercado_pago_payment_methods")
    val nonMercadoPagoPaymentMethods: List<Any?>? = listOf(),
    @SerializedName("official_store_id")
    val officialStoreId: Int? = 0,
    @SerializedName("original_price")
    val originalPrice: Double? = 0.0,
    @SerializedName("parent_item_id")
    val parentItemId: Any? = Any(),
    @SerializedName("permalink")
    val permalink: String? = "",
    @SerializedName("pictures")
    val pictures: List<Picture>? = listOf(),
    @SerializedName("price")
    val price: Double? = 0.0,
    @SerializedName("sale_terms")
    val saleTerms: List<SaleTerm>? = listOf(),
    @SerializedName("seller_address")
    val sellerAddress: SellerAddress? = SellerAddress(),
    @SerializedName("seller_contact")
    val sellerContact: Any? = Any(),
    @SerializedName("seller_id")
    val sellerId: Int? = 0,
    @SerializedName("shipping")
    val shipping: Shipping? = Shipping(),
    @SerializedName("site_id")
    val siteId: String? = "",
    @SerializedName("status")
    val status: String? = "",
    @SerializedName("sub_status")
    val subStatus: List<Any?>? = listOf(),
    @SerializedName("tags")
    val tags: List<String>? = listOf(),
    @SerializedName("thumbnail")
    val thumbnail: String? = "",
    @SerializedName("thumbnail_id")
    val thumbnailId: String? = "",
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("variations")
    val variations: List<Any?>? = listOf(),
    @SerializedName("video_id")
    val videoId: Any? = Any(),
    @SerializedName("warranty")
    val warranty: Any? = Any()
)
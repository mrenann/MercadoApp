package com.mrenann.mercadolivre.core.data.remote.model.category


import com.google.gson.annotations.SerializedName

data class Settings(
    @SerializedName("adult_content")
    val adultContent: Boolean? = false,
    @SerializedName("buyer_protection_programs")
    val buyerProtectionPrograms: List<Any?>? = listOf(),
    @SerializedName("buying_allowed")
    val buyingAllowed: Boolean? = false,
    @SerializedName("buying_modes")
    val buyingModes: List<String?>? = listOf(),
    @SerializedName("catalog_domain")
    val catalogDomain: String? = "",
    @SerializedName("coverage_areas")
    val coverageAreas: String? = "",
    @SerializedName("currencies")
    val currencies: List<String?>? = listOf(),
    @SerializedName("fragile")
    val fragile: Boolean? = false,
    @SerializedName("immediate_payment")
    val immediatePayment: String? = "",
    @SerializedName("item_conditions")
    val itemConditions: List<String?>? = listOf(),
    @SerializedName("items_reviews_allowed")
    val itemsReviewsAllowed: Boolean? = false,
    @SerializedName("listing_allowed")
    val listingAllowed: Boolean? = false,
    @SerializedName("max_description_length")
    val maxDescriptionLength: Int? = 0,
    @SerializedName("max_pictures_per_item")
    val maxPicturesPerItem: Int? = 0,
    @SerializedName("max_pictures_per_item_var")
    val maxPicturesPerItemVar: Int? = 0,
    @SerializedName("max_sub_title_length")
    val maxSubTitleLength: Int? = 0,
    @SerializedName("max_title_length")
    val maxTitleLength: Int? = 0,
    @SerializedName("max_variations_allowed")
    val maxVariationsAllowed: Int? = 0,
    @SerializedName("maximum_price")
    val maximumPrice: Any? = Any(),
    @SerializedName("maximum_price_currency")
    val maximumPriceCurrency: String? = "",
    @SerializedName("minimum_price")
    val minimumPrice: Int? = 0,
    @SerializedName("minimum_price_currency")
    val minimumPriceCurrency: String? = "",
    @SerializedName("mirror_category")
    val mirrorCategory: Any? = Any(),
    @SerializedName("mirror_master_category")
    val mirrorMasterCategory: Any? = Any(),
    @SerializedName("mirror_slave_categories")
    val mirrorSlaveCategories: List<Any?>? = listOf(),
    @SerializedName("price")
    val price: String? = "",
    @SerializedName("reservation_allowed")
    val reservationAllowed: String? = "",
    @SerializedName("restrictions")
    val restrictions: List<Any?>? = listOf(),
    @SerializedName("rounded_address")
    val roundedAddress: Boolean? = false,
    @SerializedName("seller_contact")
    val sellerContact: String? = "",
    @SerializedName("shipping_options")
    val shippingOptions: List<String?>? = listOf(),
    @SerializedName("shipping_profile")
    val shippingProfile: String? = "",
    @SerializedName("show_contact_information")
    val showContactInformation: Boolean? = false,
    @SerializedName("simple_shipping")
    val simpleShipping: String? = "",
    @SerializedName("status")
    val status: String? = "",
    @SerializedName("stock")
    val stock: String? = "",
    @SerializedName("sub_vertical")
    val subVertical: Any? = Any(),
    @SerializedName("subscribable")
    val subscribable: Boolean? = false,
    @SerializedName("tags")
    val tags: List<Any?>? = listOf(),
    @SerializedName("vertical")
    val vertical: String? = "",
    @SerializedName("vip_subdomain")
    val vipSubdomain: String? = ""
)
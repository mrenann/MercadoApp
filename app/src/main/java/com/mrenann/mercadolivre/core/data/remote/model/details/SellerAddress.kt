package com.mrenann.mercadolivre.core.data.remote.model.details


import com.google.gson.annotations.SerializedName

data class SellerAddress(
    @SerializedName("city")
    val city: City? = City(),
    @SerializedName("country")
    val country: Country? = Country(),
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("search_location")
    val searchLocation: SearchLocation? = SearchLocation(),
    @SerializedName("state")
    val state: State? = State()
)
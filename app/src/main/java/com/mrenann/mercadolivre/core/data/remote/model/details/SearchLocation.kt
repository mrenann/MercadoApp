package com.mrenann.mercadolivre.core.data.remote.model.details


import com.google.gson.annotations.SerializedName

data class SearchLocation(
    @SerializedName("city")
    val city: City? = City(),
    @SerializedName("neighborhood")
    val neighborhood: Neighborhood? = Neighborhood(),
    @SerializedName("state")
    val state: State? = State()
)
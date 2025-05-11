package com.mrenann.mercadolivre.core.data.remote.model.search

import com.google.gson.annotations.SerializedName

data class Sort(
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("name")
    val name: String? = "",
)

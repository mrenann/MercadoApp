package com.mrenann.mercadolivre.core.data.remote.model.details


import com.google.gson.annotations.SerializedName

data class StructValue(
    @SerializedName("number")
    val number: Double? = 0.0,
    @SerializedName("unit")
    val unit: String? = ""
)
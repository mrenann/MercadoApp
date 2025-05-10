package com.mrenann.mercadolivre.core.data.remote.model


import com.google.gson.annotations.SerializedName

data class ValueStruct(
    @SerializedName("number")
    val number: Double? = 0.0,
    @SerializedName("unit")
    val unit: String? = ""
)
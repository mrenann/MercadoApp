package com.mrenann.mercadolivre.core.data.remote.model.details


import com.google.gson.annotations.SerializedName

data class ValueStruct(
    @SerializedName("number")
    val number: Int? = 0,
    @SerializedName("unit")
    val unit: String? = ""
)
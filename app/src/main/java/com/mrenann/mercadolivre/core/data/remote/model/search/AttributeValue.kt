package com.mrenann.mercadolivre.core.data.remote.model.search

import com.google.gson.annotations.SerializedName

data class AttributeValue(
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("source")
    val source: Long? = 0,
    @SerializedName("struct")
    val struct: Struct? = Struct(),
)

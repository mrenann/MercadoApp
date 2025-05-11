package com.mrenann.mercadolivre.core.data.remote.model.details


import com.google.gson.annotations.SerializedName

data class Attribute(
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("value_id")
    val valueId: String? = "",
    @SerializedName("value_name")
    val valueName: String? = "",
    @SerializedName("value_type")
    val valueType: String? = "",
    @SerializedName("values")
    val values: List<Value?>? = listOf()
)
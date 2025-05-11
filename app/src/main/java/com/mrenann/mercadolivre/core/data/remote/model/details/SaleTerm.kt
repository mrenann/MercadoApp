package com.mrenann.mercadolivre.core.data.remote.model.details


import com.google.gson.annotations.SerializedName

data class SaleTerm(
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("value_id")
    val valueId: Any? = Any(),
    @SerializedName("value_name")
    val valueName: String? = "",
    @SerializedName("value_struct")
    val valueStruct: ValueStruct? = ValueStruct(),
    @SerializedName("value_type")
    val valueType: String? = "",
    @SerializedName("values")
    val values: List<Value>? = listOf()
)
package com.mrenann.mercadolivre.core.data.remote.model


import com.google.gson.annotations.SerializedName

data class FilterValue(
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("path_from_root")
    val pathFromRoot: List<PathFromRoot?>? = listOf()
)
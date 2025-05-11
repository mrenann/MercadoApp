package com.mrenann.mercadolivre.core.data.remote.model.search

import com.google.gson.annotations.SerializedName
import com.mrenann.mercadolivre.core.data.remote.model.details.PathFromRoot

data class FilterValue(
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("path_from_root")
    val pathFromRoot: List<PathFromRoot?>? = listOf(),
)

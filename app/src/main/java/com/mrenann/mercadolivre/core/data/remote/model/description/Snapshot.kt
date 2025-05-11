package com.mrenann.mercadolivre.core.data.remote.model.description

import com.google.gson.annotations.SerializedName

data class Snapshot(
    @SerializedName("height")
    val height: Int? = 0,
    @SerializedName("status")
    val status: String? = "",
    @SerializedName("url")
    val url: String? = "",
    @SerializedName("width")
    val width: Int? = 0
)
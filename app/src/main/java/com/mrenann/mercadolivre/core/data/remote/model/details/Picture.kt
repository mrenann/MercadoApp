package com.mrenann.mercadolivre.core.data.remote.model.details


import com.google.gson.annotations.SerializedName

data class Picture(
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("max_size")
    val maxSize: String? = "",
    @SerializedName("quality")
    val quality: String? = "",
    @SerializedName("secure_url")
    val secureUrl: String? = "",
    @SerializedName("size")
    val size: String? = "",
    @SerializedName("url")
    val url: String? = ""
)
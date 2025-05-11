package com.mrenann.mercadolivre.core.data.remote.model

import com.google.gson.annotations.SerializedName

data class Seller(
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("nickname")
    val nickname: String? = "",
)

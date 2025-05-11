package com.mrenann.mercadolivre.core.data.remote.model.search

import com.google.gson.annotations.SerializedName

data class Paging(
    @SerializedName("limit")
    val limit: Int? = 0,
    @SerializedName("offset")
    val offset: Int? = 0,
    @SerializedName("primary_results")
    val primaryResults: Int? = 0,
    @SerializedName("total")
    val total: Int? = 0,
)

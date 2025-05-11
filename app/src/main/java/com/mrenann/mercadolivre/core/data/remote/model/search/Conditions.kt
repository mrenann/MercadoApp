package com.mrenann.mercadolivre.core.data.remote.model.search

import com.google.gson.annotations.SerializedName

data class Conditions(
    @SerializedName("context_restrictions")
    val contextRestrictions: List<String?>? = listOf(),
    @SerializedName("eligible")
    val eligible: Boolean? = false,
    @SerializedName("end_time")
    val endTime: String? = "",
    @SerializedName("start_time")
    val startTime: String? = "",
)

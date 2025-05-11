package com.mrenann.mercadolivre.core.data.remote.response


import com.google.gson.annotations.SerializedName
import com.mrenann.mercadolivre.core.data.remote.model.description.Snapshot

data class ItemDescriptionResponse(
    @SerializedName("date_created")
    val dateCreated: String? = "",
    @SerializedName("last_updated")
    val lastUpdated: String? = "",
    @SerializedName("plain_text")
    val plainText: String? = "",
    @SerializedName("snapshot")
    val snapshot: Snapshot? = Snapshot(),
    @SerializedName("text")
    val text: String? = ""
)
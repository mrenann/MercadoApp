package com.mrenann.mercadolivre.core.data.remote.model.category


import com.google.gson.annotations.SerializedName

data class ChannelsSetting(
    @SerializedName("channel")
    val channel: String? = "",
    @SerializedName("settings")
    val settings: SettingsFromChannel? = SettingsFromChannel()
)
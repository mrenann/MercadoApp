package com.mrenann.mercadolivre.core.data.remote.response


import com.google.gson.annotations.SerializedName
import com.mrenann.mercadolivre.core.data.remote.model.category.ChannelsSetting
import com.mrenann.mercadolivre.core.data.remote.model.category.Settings
import com.mrenann.mercadolivre.core.data.remote.model.details.PathFromRoot

data class ItemCategoryResponse(
    @SerializedName("attributable")
    val attributable: Boolean? = false,
    @SerializedName("attribute_types")
    val attributeTypes: String? = "",
    @SerializedName("channels_settings")
    val channelsSettings: List<ChannelsSetting>? = listOf(),
    @SerializedName("children_categories")
    val childrenCategories: List<Any?>? = listOf(),
    @SerializedName("date_created")
    val dateCreated: String? = "",
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("meta_categ_id")
    val metaCategId: Any? = Any(),
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("path_from_root")
    val pathFromRoot: List<PathFromRoot>? = listOf(),
    @SerializedName("permalink")
    val permalink: Any? = Any(),
    @SerializedName("picture")
    val picture: String? = "",
    @SerializedName("settings")
    val settings: Settings? = Settings(),
    @SerializedName("total_items_in_this_category")
    val totalItemsInThisCategory: Int? = 0
)
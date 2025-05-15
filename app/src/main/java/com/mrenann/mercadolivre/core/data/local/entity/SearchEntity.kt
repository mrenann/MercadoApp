package com.mrenann.mercadolivre.core.data.local.entity

import androidx.room.Entity

@Entity(
    tableName = "Search",
    primaryKeys = ["query"]
)
data class SearchEntity(
    val query: String
)
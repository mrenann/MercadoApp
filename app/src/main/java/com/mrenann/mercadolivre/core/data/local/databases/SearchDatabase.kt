package com.mrenann.mercadolivre.core.data.local.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mrenann.mercadolivre.core.data.local.dao.SearchDao
import com.mrenann.mercadolivre.core.data.local.entity.SearchEntity

@Database(
    entities = [SearchEntity::class],
    version = 2,
    exportSchema = false
)
abstract class SearchDatabase : RoomDatabase() {
    abstract fun searchDao(): SearchDao
}
package com.mrenann.mercadolivre.core.di

import androidx.room.Room
import com.mrenann.mercadolivre.core.data.local.dao.SearchDao
import com.mrenann.mercadolivre.core.data.local.databases.SearchDatabase
import com.mrenann.mercadolivre.core.utils.Constants.ListDatabaseName
import org.koin.dsl.module

val roomModule = module {
    single {
        Room.databaseBuilder(get(), SearchDatabase::class.java, ListDatabaseName)
            .build()
    }

    single<SearchDao> {
        val database = get<SearchDatabase>()
        database.searchDao()
    }

}
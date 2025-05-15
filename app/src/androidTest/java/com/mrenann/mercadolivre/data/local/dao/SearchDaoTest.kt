package com.mrenann.mercadolivre.data.local.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.mrenann.mercadolivre.core.data.local.dao.SearchDao
import com.mrenann.mercadolivre.core.data.local.databases.SearchDatabase
import com.mrenann.mercadolivre.core.data.local.entity.SearchEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class SearchDaoTest {

    private lateinit var database: SearchDatabase
    private lateinit var searchDao: SearchDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            SearchDatabase::class.java
        ).allowMainThreadQueries()
            .build()

        searchDao = database.searchDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun SearchDao_insertSearchAndRetrieveSearches() = runTest {
        val searchQuery = "iphone"
        searchDao.insertNewSearch(SearchEntity(searchQuery))

        val searches = searchDao.getLastSearches().first()
        assertTrue(searches.contains(searchQuery))
    }

    @Test
    fun SearchDao_insertDuplicateSearch_ShouldIgnore() = runTest {
        val searchQuery = "camisa"

        searchDao.insertNewSearch(SearchEntity(searchQuery))
        searchDao.insertNewSearch(SearchEntity(searchQuery))

        val searches = searchDao.getLastSearches().first()

        assertEquals(1, searches.size)
        assertEquals(searchQuery, searches[0])
    }

    @Test
    fun SearchDao_retrieveEmptySearches_WhenDatabaseIsEmpty() = runTest {
        val searches = searchDao.getLastSearches().first()
        assertTrue(searches.isEmpty())
    }
}
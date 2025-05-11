package com.mrenann.mercadolivre.searchScreen.data.repository

import app.cash.turbine.test
import com.mrenann.mercadolivre.core.data.remote.response.SearchQueryResponse
import com.mrenann.mercadolivre.core.utils.Resource
import com.mrenann.mercadolivre.searchScreen.domain.source.SearchDataSource
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class SearchRepositoryImplTest {

    @Test
    fun `should repository delegate to dataSource`() = runTest {
        val fakeResponse = SearchQueryResponse()
        val dataSource = mockk<SearchDataSource>()
        coEvery { dataSource.search("arroz") } returns flowOf(Resource.Success(fakeResponse))

        val repository = SearchRepositoryImpl(dataSource)

        repository.searchProducts("arroz").test {
            assertEquals(Resource.Success(fakeResponse), awaitItem())
            awaitComplete()
        }
    }
}

package com.mrenann.mercadolivre.searchScreen.data.source

import android.util.Log
import app.cash.turbine.test
import com.mrenann.mercadolivre.core.data.remote.MockApiService
import com.mrenann.mercadolivre.core.data.remote.response.SearchQueryResponse
import com.mrenann.mercadolivre.core.utils.Resource
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class SearchDataSourceImplTest {

    private val service: MockApiService = mockk()
    private lateinit var dataSource: SearchDataSourceImpl

    @Before
    fun setup() {
        dataSource = SearchDataSourceImpl(service)

        mockkStatic(Log::class)
        every { Log.d(any(), any()) } returns 0
        every { Log.e(any(), any<String>()) } returns 0
        every { Log.e(any(), any<String>(), any()) } returns 0
        every { Log.w(any(), any<String>()) } returns 0
        every { Log.w(any(), any<String>(), any()) } returns 0
    }

    @Test
    fun `should emit Loading and Success when service returns data`() = runTest {
        val expectedResponse = SearchQueryResponse()
        val responseFlow = flowOf(expectedResponse)

        coEvery { service.searchProducts("arroz") } returns responseFlow

        dataSource.search("arroz").test {
            assertEquals(Resource.Loading, awaitItem())
            assertEquals(Resource.Success(expectedResponse), awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `should emit Loading and Error when service throws exception`() = runTest {
        val exception =
            NoSuchElementException("Nenhum resultado encontrado para o termo informado.")
        coEvery { service.searchProducts("") } throws exception

        dataSource.search("").test {
            assertEquals(Resource.Loading, awaitItem())
            assertEquals(
                exception.message.toString(), (awaitItem() as Resource.Error).message
            )
            awaitComplete()

        }
    }
}

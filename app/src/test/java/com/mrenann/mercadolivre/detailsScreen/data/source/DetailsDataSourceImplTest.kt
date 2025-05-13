package com.mrenann.mercadolivre.detailsScreen.data.source

import android.util.Log
import app.cash.turbine.test
import com.mrenann.mercadolivre.core.data.remote.MockApiService
import com.mrenann.mercadolivre.core.data.remote.response.ItemCategoryResponse
import com.mrenann.mercadolivre.core.data.remote.response.ItemDescriptionResponse
import com.mrenann.mercadolivre.core.data.remote.response.ItemDetailsResponse
import com.mrenann.mercadolivre.core.utils.Resource
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class DetailsDataSourceImplTest {

    private val service: MockApiService = mockk()
    private lateinit var dataSource: DetailsDataSourceImpl

    @Before
    fun setup() {
        dataSource = DetailsDataSourceImpl(service)

        mockkStatic(Log::class)
        every { Log.d(any(), any()) } returns 0
        every { Log.e(any(), any<String>()) } returns 0
        every { Log.e(any(), any<String>(), any()) } returns 0
        every { Log.w(any(), any<String>()) } returns 0
        every { Log.w(any(), any<String>(), any()) } returns 0
    }

    @Test
    fun `getItem should emit Loading then Success in order when service returns data`() = runTest {
        val itemId = "MLA2005705454"
        val expectedResponse = ItemDetailsResponse(
            id = "MLA2005705454",
            title = "Apple iPhone 16 Pro Max (256 Gb) - Titanio Del Desierto",
        )
        coEvery { service.getItem(itemId) } returns flowOf(expectedResponse)

        dataSource.getItem(itemId).test {
            val loading = awaitItem()
            val success = awaitItem()
            awaitComplete()

            assertEquals(Resource.Loading, loading)
            assertEquals(Resource.Success(expectedResponse), success)
        }
    }


    @Test
    fun `getItem should emit Loading then Error in order when service throws exception`() =
        runTest {
            val itemId = "MLA2005705454"
            val exception = IOException("Erro de conexão. Verifique sua internet.")
            coEvery { service.getItem(itemId) } throws exception

            dataSource.getItem(itemId).test {
                val loading = awaitItem()
                val error = awaitItem()
                awaitComplete()

                assertEquals(Resource.Loading, loading)
                assertEquals(exception.message, (error as Resource.Error).message)
            }
        }

    @Test
    fun `getItemCategory should emit Loading and Success when service returns data`() = runTest {
        val itemId = "MLA2005705454"
        val expectedResponse = mockk<ItemCategoryResponse>()
        coEvery { service.getItemCategory(itemId) } returns flowOf(expectedResponse)

        dataSource.getItemCategory(itemId).test {
            assertEquals(Resource.Loading, awaitItem())
            assertEquals(Resource.Success(expectedResponse), awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `getItemCategory should emit Loading and Error when service throws exception`() = runTest {
        val itemId = "MLA2005705454"
        val exception = IOException("Erro de conexão. Verifique sua internet.")
        coEvery { service.getItemCategory(itemId) } throws exception

        dataSource.getItemCategory(itemId).test {
            assertEquals(Resource.Loading, awaitItem())
            assertEquals(exception.message, (awaitItem() as Resource.Error).message)
            awaitComplete()
        }
    }

    @Test
    fun `getItemDescription should emit Loading and Success when service returns data`() = runTest {
        val itemId = "MLA2005705454"
        val expectedResponse = mockk<ItemDescriptionResponse>()
        coEvery { service.getItemDescription(itemId) } returns flowOf(expectedResponse)

        dataSource.getItemDescription(itemId).test {
            assertEquals(Resource.Loading, awaitItem())
            assertEquals(Resource.Success(expectedResponse), awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `getItemDescription should emit Loading and Error when service throws exception`() =
        runTest {
            val itemId = "MLA2005705454"
            val exception = IOException("Erro de conexão. Verifique sua internet.")
            coEvery { service.getItemDescription(itemId) } throws exception

            dataSource.getItemDescription(itemId).test {
                assertEquals(Resource.Loading, awaitItem())
                assertEquals(exception.message, (awaitItem() as Resource.Error).message)
                awaitComplete()
            }
        }
}
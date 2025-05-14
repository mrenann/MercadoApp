package com.mrenann.mercadolivre.detailsScreen.data.repository

import app.cash.turbine.test
import com.mrenann.mercadolivre.core.data.remote.mappers.toDomain
import com.mrenann.mercadolivre.core.data.remote.response.ItemCategoryResponse
import com.mrenann.mercadolivre.core.data.remote.response.ItemDescriptionResponse
import com.mrenann.mercadolivre.core.data.remote.response.ItemDetailsResponse
import com.mrenann.mercadolivre.core.utils.Resource
import com.mrenann.mercadolivre.detailsScreen.domain.repository.DetailsRepository
import com.mrenann.mercadolivre.detailsScreen.domain.source.DetailsDataSource
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DetailsRepositoryImplTest {
    private val dataSource = mockk<DetailsDataSource>()
    private lateinit var repository: DetailsRepository

    @Before
    fun setup() {
        repository = DetailsRepositoryImpl(dataSource)
    }

    @Test
    fun `getItem should emit Loading and Success with mapped domain object`() = runTest {
        val itemId = "MLA2005705454"
        val response = ItemDetailsResponse(
            id = itemId,
            title = "Produto Teste",
            price = 100.0,
            currencyId = "BRL",
            condition = "new",
            originalPrice = 150.0,
            pictures = emptyList(),
        )
        val expectedDomain = response.toDomain()

        coEvery { dataSource.getItem(itemId) } returns flow {
            emit(Resource.Loading)
            emit(Resource.Success(response))
        }

        repository.getItem(itemId).test {
            assertEquals(Resource.Loading, awaitItem())
            val result = awaitItem()
            assertTrue(result is Resource.Success)
            assertEquals(expectedDomain, (result as Resource.Success).data)
            awaitComplete()
        }
    }

    @Test
    fun `getItem should emit Loading and Error when data source fails`() = runTest {
        val itemId = "MLA2005705454"
        val errorMessage = "Erro de conexão"

        coEvery { dataSource.getItem(itemId) } returns flow {
            emit(Resource.Loading)
            emit(Resource.Error(errorMessage))
        }

        repository.getItem(itemId).test {
            assertEquals(Resource.Loading, awaitItem())
            assertEquals(errorMessage, (awaitItem() as Resource.Error).message)
            awaitComplete()
        }
    }

    @Test
    fun `getItemCategory should emit Loading and Success with mapped domain object`() = runTest {
        val itemId = "MLA2005705454"
        val response = ItemCategoryResponse(
            id = "ML1234",
            name = "Categoria"
        )
        val expectedDomain = response.toDomain()

        coEvery { dataSource.getItemCategory(itemId) } returns flow {
            emit(Resource.Loading)
            emit(Resource.Success(response))
        }

        repository.getItemCategory(itemId).test {
            assertEquals(Resource.Loading, awaitItem())
            val result = awaitItem()
            assertTrue(result is Resource.Success)
            assertEquals(expectedDomain, (result as Resource.Success).data)
            awaitComplete()
        }
    }

    @Test
    fun `getItemCategory should emit Loading and Error when data source fails`() = runTest {
        val itemId = "MLA2005705454"
        val errorMessage = "Categoria nao encontrada"

        coEvery { dataSource.getItemCategory(itemId) } returns flow {
            emit(Resource.Loading)
            emit(Resource.Error(errorMessage))
        }

        repository.getItemCategory(itemId).test {
            assertEquals(Resource.Loading, awaitItem())
            assertEquals(errorMessage, (awaitItem() as Resource.Error).message)
            awaitComplete()
        }
    }

    @Test
    fun `getItemDescription should emit Loading and Success with mapped domain object`() = runTest {
        val itemId = "MLA2005705454"
        val response = ItemDescriptionResponse(
            plainText = "."
        )
        val expectedDomain = response.toDomain()

        coEvery { dataSource.getItemDescription(itemId) } returns flow {
            emit(Resource.Loading)
            emit(Resource.Success(response))
        }

        repository.getItemDescription(itemId).test {
            assertEquals(Resource.Loading, awaitItem())
            val result = awaitItem()
            assertTrue(result is Resource.Success)
            assertEquals(expectedDomain, (result as Resource.Success).data)
            awaitComplete()
        }
    }

    @Test
    fun `getItemDescription should emit Loading and Error when data source fails`() = runTest {
        val itemId = "MLA2005705454"
        val errorMessage = "Erro desconhecido"

        coEvery { dataSource.getItemDescription(itemId) } returns flow {
            emit(Resource.Loading)
            emit(Resource.Error(errorMessage))
        }

        repository.getItemDescription(itemId).test {
            assertEquals(Resource.Loading, awaitItem())
            assertEquals(errorMessage, (awaitItem() as Resource.Error).message)
            awaitComplete()
        }
    }

    @Test
    fun `getItem should preserve Loading state during mapping`() = runTest {
        val itemId = "MLA2005705454"
        val response = ItemDetailsResponse(
            id = itemId,
            title = "Iphone",
            price = 100.0,
            currencyId = "BRL",
            condition = "new",
            originalPrice = 150.0,
            pictures = emptyList(),
        )

        coEvery { dataSource.getItem(itemId) } returns flow {
            emit(Resource.Loading)
            delay(100)
            emit(Resource.Success(response))
        }

        repository.getItem(itemId).test {
            assertEquals(Resource.Loading, awaitItem())
            assertTrue(awaitItem() is Resource.Success)
            awaitComplete()
        }
    }
}
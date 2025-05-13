package com.mrenann.mercadolivre.detailsScreen.data.usecase

import app.cash.turbine.test
import com.mrenann.mercadolivre.core.domain.model.Category
import com.mrenann.mercadolivre.core.domain.model.ProductDetails
import com.mrenann.mercadolivre.core.utils.Resource
import com.mrenann.mercadolivre.detailsScreen.domain.repository.DetailsRepository
import com.mrenann.mercadolivre.detailsScreen.domain.usecase.GetProductUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetProductUseCaseImplTest {
    private val repository = mockk<DetailsRepository>()
    private lateinit var useCase: GetProductUseCase

    @Before
    fun setup() {
        useCase = GetProductUseCaseImpl(repository)
    }

    @Test
    fun `invoke should emit Loading when any source is loading`() = runTest {
        val itemId = "MLA2005705454"
        mockk<ProductDetails>()

        coEvery { repository.getItem(itemId) } returns flowOf(Resource.Loading)
        coEvery { repository.getItemCategory(itemId) } returns flowOf(Resource.Success(mockk()))
        coEvery { repository.getItemDescription(itemId) } returns flowOf(Resource.Success("desc"))

        useCase(itemId).test {
            assertEquals(Resource.Loading, awaitItem())
            awaitComplete()
        }
    }


    @Test
    fun `invoke should emit Success with partial data and errors when some sources fail`() =
        runTest {
            val itemId = "MLA2005705454"
            val productDetails = mockk<ProductDetails>()
            val categoryError = "Categoria nao encontrada"
            val descError = "Descricao nao encontrado"

            coEvery { repository.getItem(itemId) } returns flowOf(Resource.Success(productDetails))
            coEvery { repository.getItemCategory(itemId) } returns flowOf(
                Resource.Error(
                    categoryError
                )
            )
            coEvery { repository.getItemDescription(itemId) } returns flowOf(
                Resource.Error(
                    descError
                )
            )

            useCase(itemId).test {
                val result = awaitItem()
                assertTrue(result is Resource.Success)
                val detailedProduct = (result as Resource.Success).data
                assertEquals(productDetails, detailedProduct.item)
                assertEquals(null, detailedProduct.category)
                assertEquals(null, detailedProduct.description)
                assertEquals(listOf(categoryError, descError), detailedProduct.errors)
                awaitComplete()
            }
        }

    @Test
    fun `invoke should emit Success with all nulls when all sources fail`() = runTest {
        val itemId = "MLA2005705454"
        val itemError = "Item nao encontrado"
        val categoryError = "Categoria nao encontrada"
        val descError = "Descricao nao encontrado"

        coEvery { repository.getItem(itemId) } returns flowOf(Resource.Error(itemError))
        coEvery { repository.getItemCategory(itemId) } returns flowOf(Resource.Error(categoryError))
        coEvery { repository.getItemDescription(itemId) } returns flowOf(Resource.Error(descError))

        useCase(itemId).test {
            val result = awaitItem()
            assertTrue(result is Resource.Success)
            val detailedProduct = (result as Resource.Success).data
            assertEquals(null, detailedProduct.item)
            assertEquals(null, detailedProduct.category)
            assertEquals(null, detailedProduct.description)
            assertEquals(listOf(itemError, categoryError, descError), detailedProduct.errors)
            awaitComplete()
        }
    }

    @Test
    fun `invoke should emit Loading multiple times when sources load at different times`() =
        runTest {
            val itemId = "MLA2005705454"
            val productDetails = mockk<ProductDetails>()
            val category = mockk<Category>()
            val description = "Descricao"

            coEvery { repository.getItem(itemId) } returns flow {
                emit(Resource.Loading)
                delay(50)
                emit(Resource.Success(productDetails))
            }
            coEvery { repository.getItemCategory(itemId) } returns flow {
                emit(Resource.Loading)
                delay(100)
                emit(Resource.Success(category))
            }
            coEvery { repository.getItemDescription(itemId) } returns flow {
                emit(Resource.Loading)
                delay(150)
                emit(Resource.Success(description))
            }

            useCase(itemId).test {
                assertEquals(Resource.Loading, awaitItem())
                assertEquals(Resource.Loading, awaitItem())
                assertEquals(Resource.Loading, awaitItem())

                val result = awaitItem()
                assertTrue(result is Resource.Success)
                awaitComplete()
            }
        }

    @Test
    fun `invoke should handle empty description response`() = runTest {
        val itemId = "MLA2005705454"
        val productDetails = mockk<ProductDetails>()
        val category = mockk<Category>()

        coEvery { repository.getItem(itemId) } returns flowOf(Resource.Success(productDetails))
        coEvery { repository.getItemCategory(itemId) } returns flowOf(Resource.Success(category))
        coEvery { repository.getItemDescription(itemId) } returns flowOf(Resource.Success(""))

        useCase(itemId).test {
            val result = awaitItem()
            assertTrue(result is Resource.Success)
            val detailedProduct = (result as Resource.Success).data
            assertEquals("", detailedProduct.description)
            awaitComplete()
        }
    }

    @Test
    fun `invoke should maintain loading state until all fast sources complete`() = runTest {
        val itemId = "MLA2005705454"
        val productDetails = mockk<ProductDetails>()
        val category = mockk<Category>()

        coEvery { repository.getItem(itemId) } returns flow {
            emit(Resource.Loading)
            emit(Resource.Success(productDetails))
        }
        coEvery { repository.getItemCategory(itemId) } returns flow {
            emit(Resource.Loading)
            delay(100)
            emit(Resource.Success(category))
        }
        coEvery { repository.getItemDescription(itemId) } returns flowOf(Resource.Success("desc"))

        useCase(itemId).test {
            assertEquals(Resource.Loading, awaitItem())
            val result = awaitItem()
            assertTrue(result is Resource.Success)
            awaitComplete()
        }
    }
}
package com.mrenann.mercadolivre.detailsScreen.presentation.screenModel

import app.cash.turbine.test
import com.mrenann.mercadolivre.core.domain.model.DetailedProduct
import com.mrenann.mercadolivre.core.utils.Resource
import com.mrenann.mercadolivre.detailsScreen.domain.usecase.GetProductUseCase
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import kotlin.time.Duration.Companion.seconds

@ExperimentalCoroutinesApi
class DetailsScreenModelTest {
    private val useCase = mockk<GetProductUseCase>()
    private val dispatcher = StandardTestDispatcher()
    private lateinit var screenModel: DetailsScreenModel

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        clearAllMocks()
        screenModel = DetailsScreenModel(getProductUseCase = useCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state should be Loading`() = runTest {
        assertTrue(screenModel.state.value is DetailsScreenModel.State.Loading)
    }

    @Test
    fun `getProduct should emit Loading followed by Result on success`() = runTest {
        val productId = "MLA1116621831"
        val mockProduct = mockk<DetailedProduct>()

        coEvery { useCase.invoke(productId) } returns flowOf(
            Resource.Loading,
            Resource.Success(mockProduct)
        )

        screenModel.getProduct(productId)
        screenModel.state.test(timeout = 3.seconds) {
            assertTrue(awaitItem() is DetailsScreenModel.State.Loading)

            val resultState = awaitItem()
            assertTrue(resultState is DetailsScreenModel.State.Result)
            assertEquals(mockProduct, (resultState as DetailsScreenModel.State.Result).product)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `getProduct should emit Loading followed by Error on failure`() = runTest {
        val productId = "MLA1116621831"
        val errorMessage = "Item nao encontrado"

        coEvery { useCase.invoke(productId) } returns flowOf(
            Resource.Loading,
            Resource.Error(errorMessage)
        )

        screenModel.getProduct(productId)
        screenModel.state.test(timeout = 3.seconds) {
            assertTrue(awaitItem() is DetailsScreenModel.State.Loading)

            val errorState = awaitItem()
            assertTrue(errorState is DetailsScreenModel.State.Error)
            assertEquals(errorMessage, (errorState as DetailsScreenModel.State.Error).message)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `getProduct should handle partial success with errors`() = runTest {
        val productId = "MLA1116621831"
        val mockProduct = mockk<DetailedProduct> {
            every { errors } returns listOf("Categoria nao encontrado")
        }

        coEvery { useCase.invoke(productId) } returns flowOf(
            Resource.Loading,
            Resource.Success(mockProduct)
        )

        screenModel.getProduct(productId)
        screenModel.state.test(timeout = 3.seconds) {
            assertTrue(awaitItem() is DetailsScreenModel.State.Loading)

            val resultState = awaitItem()
            assertTrue(resultState is DetailsScreenModel.State.Result)
            assertEquals(mockProduct, (resultState as DetailsScreenModel.State.Result).product)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `getProduct should handle empty product case`() = runTest {
        val productId = "MLA131415"
        val emptyProduct = mockk<DetailedProduct> {
            every { item } returns null
            every { category } returns null
            every { description } returns null
            every { errors } returns listOf("Product not found")
        }

        coEvery { useCase.invoke(productId) } returns flowOf(
            Resource.Loading,
            Resource.Success(emptyProduct)
        )

        screenModel.getProduct(productId)
        screenModel.state.test(timeout = 3.seconds) {
            assertTrue(awaitItem() is DetailsScreenModel.State.Loading)

            val resultState = awaitItem()
            assertTrue(resultState is DetailsScreenModel.State.Result)
            assertEquals(emptyProduct, (resultState as DetailsScreenModel.State.Result).product)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `getProduct should handle network error`() = runTest {
        val productId = "MLA161718"
        val errorMessage = "Erro de conexão. Verifique sua internet."

        coEvery { useCase.invoke(productId) } returns flowOf(
            Resource.Loading,
            Resource.Error(errorMessage)
        )

        screenModel.getProduct(productId)
        screenModel.state.test(timeout = 3.seconds) {
            assertTrue(awaitItem() is DetailsScreenModel.State.Loading)

            val errorState = awaitItem()
            assertTrue(errorState is DetailsScreenModel.State.Error)
            assertEquals(errorMessage, (errorState as DetailsScreenModel.State.Error).message)

            cancelAndIgnoreRemainingEvents()
        }
    }
}
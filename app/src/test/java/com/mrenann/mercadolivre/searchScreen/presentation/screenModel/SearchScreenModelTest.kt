package com.mrenann.mercadolivre.searchScreen.presentation.screenModel

import app.cash.turbine.test
import com.mrenann.mercadolivre.core.domain.model.SearchResult
import com.mrenann.mercadolivre.core.utils.Resource
import com.mrenann.mercadolivre.searchScreen.domain.repository.SearchRepository
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
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
class SearchScreenModelTest {
    private val repository = mockk<SearchRepository>()
    private val dispatcher = StandardTestDispatcher()
    private lateinit var screenModel: SearchScreenModel

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        clearAllMocks()
        screenModel = SearchScreenModel(searchRepository = repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state should be Loading`() = runTest {
        assertTrue(screenModel.state.value is SearchScreenModel.State.Loading)
    }

    @Test
    fun `searchProducts should emit Loading followed by Result on success`() = runTest {
        val query = "arroz"
        val mockItems = listOf(
            SearchResult(
                id = "1",
                title = "arroz",
                thumbnail = "",
                originalPrice = 20.0,
                freeShipping = true,
                currencyId = "BRL",
                price = 10.0
            )
        )

        coEvery { repository.searchProducts(query) } returns flowOf(
            Resource.Loading,
            Resource.Success(mockItems)
        )

        screenModel.searchProducts(query)
        screenModel.state.test(timeout = 3.seconds) {
            assertTrue(awaitItem() is SearchScreenModel.State.Loading)

            val resultState = awaitItem()
            assertTrue(resultState is SearchScreenModel.State.Result)

            val result = resultState as SearchScreenModel.State.Result
            assertEquals(query, result.state.query)
            assertEquals(mockItems, result.state.items)

            cancelAndIgnoreRemainingEvents()
        }
    }


    @Test
    fun `searchProducts should emit Loading followed by Error on failure`() = runTest {
        val query = "invalid search"
        val errorMessage = "Network error"

        coEvery { repository.searchProducts(query) } returns flowOf(
            Resource.Loading,
            Resource.Error(errorMessage)
        )
        screenModel.searchProducts(query)
        screenModel.state.test(timeout = 3.seconds) {
            val loadingState = awaitItem()
            assertTrue(loadingState is SearchScreenModel.State.Loading)

            val errorState = awaitItem()
            assertTrue(errorState is SearchScreenModel.State.Error)
            assertEquals(errorMessage, (errorState as SearchScreenModel.State.Error).message)

            cancelAndIgnoreRemainingEvents()
        }
    }


    @Test
    fun `searchProducts should handle empty results properly`() = runTest {
        val query = "feijao"

        coEvery { repository.searchProducts(query) } returns flowOf(
            Resource.Loading,
            Resource.Error(
                message = "Não encontramos resultados para: $query"
            )
        )

        screenModel.searchProducts(query)
        screenModel.state.test(timeout = 3.seconds) {
            advanceUntilIdle()

            val loadingState = awaitItem()
            assertTrue(loadingState is SearchScreenModel.State.Loading)

            val resultState = awaitItem()
            assertTrue(resultState is SearchScreenModel.State.Error)

            val result = resultState as SearchScreenModel.State.Error
            assertEquals("Não encontramos resultados para: feijao", result.message)

            cancelAndIgnoreRemainingEvents()
        }
    }
}
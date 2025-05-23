package com.mrenann.mercadolivre.searchScreen.data.repository

import app.cash.turbine.test
import com.mrenann.mercadolivre.core.data.remote.mappers.toDomain
import com.mrenann.mercadolivre.core.data.remote.model.search.Result
import com.mrenann.mercadolivre.core.data.remote.model.search.Shipping
import com.mrenann.mercadolivre.core.data.remote.response.SearchQueryResponse
import com.mrenann.mercadolivre.core.utils.Resource
import com.mrenann.mercadolivre.searchScreen.domain.repository.SearchRepository
import com.mrenann.mercadolivre.searchScreen.domain.source.SearchDataSource
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class SearchRepositoryImplTest {

    private val dataSource = mockk<SearchDataSource>()
    private lateinit var repository: SearchRepository

    @Before
    fun setup() {
        repository = SearchRepositoryImpl(dataSource)
    }

    @Test
    fun `when search is called, repository should return mapped domain model from dataSource`() =
        runTest {
            val fakeResponse = SearchQueryResponse(
                results = listOf(
                    Result(
                        id = "1",
                        title = "arroz",
                        thumbnail = "",
                        originalPrice = 20.0,
                        shipping = Shipping(
                            freeShipping = true
                        ),
                        currencyId = "BRL",
                        price = 10.0,
                    )
                )
            )



            coEvery { dataSource.search("arroz") } returns flowOf(Resource.Success(fakeResponse))


            val expected = Resource.Success(fakeResponse.results?.map { it.toDomain() })
            repository.searchProducts("arroz").test {
                assertEquals(expected, awaitItem())
                awaitComplete()
            }
        }

    @Test
    fun `when dataSource emits Loading, repository should emit Loading`() = runTest {
        coEvery { dataSource.search("arroz") } returns flowOf(Resource.Loading)

        repository.searchProducts("arroz").test {
            assertEquals(Resource.Loading, awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `when dataSource emits Error, repository should emit Error with same message`() = runTest {
        val errorMessage = "Algo deu errado"
        coEvery { dataSource.search("arroz") } returns flowOf(Resource.Error(errorMessage))

        repository.searchProducts("arroz").test {
            assertEquals(Resource.Error(errorMessage), awaitItem())
            awaitComplete()
        }
    }


}

package com.mrenann.mercadolivre.core.data.remote

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.mrenann.mercadolivre.core.data.remote.model.arrozString
import com.mrenann.mercadolivre.core.data.remote.response.SearchQueryResponse
import com.mrenann.mercadolivre.core.utils.JsonUtils
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
import java.io.IOException

class MockApiServiceImplTest {

    private lateinit var mockContext: Context
    private lateinit var service: MockApiServiceImpl
    private val gson = Gson()

    @Before
    fun setUp() {
        mockContext = mockk(relaxed = true)
        service = MockApiServiceImpl(mockContext)

        mockkStatic(Log::class)
        every { Log.d(any(), any()) } returns 0
        every { Log.e(any(), any<String>()) } returns 0
        every { Log.e(any(), any<String>(), any()) } returns 0
        every { Log.w(any(), any<String>()) } returns 0
        every { Log.w(any(), any<String>(), any()) } returns 0

        mockkObject(JsonUtils)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `should emit SearchQueryResponse when searchProducts is called with valid query 'arroz'`() =
        runTest {
            val query =
                "arroz"
            val fileName = "search-MLA-$query.json"

            val expectedResponseObject =
                gson.fromJson(arrozString, SearchQueryResponse::class.java)

            every { JsonUtils.getJsonDataFromAsset(mockContext, fileName) } returns arrozString

            val result = service.searchProducts(query).first()

            assertNotNull(result)

            val actualResponseObject =
                gson.fromJson(arrozString, result::class.java)
            if (actualResponseObject is SearchQueryResponse && expectedResponseObject is SearchQueryResponse) {
                assertEquals(expectedResponseObject.query, actualResponseObject.query)
                assertEquals(expectedResponseObject.results, actualResponseObject.results)
            }


            verify { Log.d("MockApiService", "Buscando arquivo: $fileName") }
            verify { JsonUtils.getJsonDataFromAsset(mockContext, fileName) }
        }

    @Test(expected = IllegalArgumentException::class)
    fun `should throw IllegalArgumentException when searchProducts is called with empty query`() =
        runTest {
            try {
                service.searchProducts("").toList()
            } catch (e: IllegalArgumentException) {
                assertEquals("Termo de busca não pode ser vazio", e.message)
                throw e
            }
            fail("Deveria ter lançado IllegalArgumentException")
        }

    @Test
    fun `should throw IllegalArgumentException with correct message when searchProducts is called with empty query`() =
        runTest {
            try {
                service.searchProducts("").first()
                fail("IllegalArgumentException esperada mas não lançada.")
            } catch (e: IllegalArgumentException) {
                assertEquals("Termo de busca não pode ser vazio", e.message)
            }
        }


    @Test(expected = NoSuchElementException::class)
    fun `should throw NoSuchElementException when searchProducts is called with unavailable query`() =
        runTest {
            val query = "produtoInexistente"
            try {
                service.searchProducts(query).toList()
            } catch (e: NoSuchElementException) {
                assertEquals("Não encontramos resultados para: $query", e.message)
                verify { Log.w("MockApiService", "Termo de busca não disponível: $query") }
                throw e
            }
            fail("Deveria ter lançado NoSuchElementException")
        }

    @Test
    fun `should throw NoSuchElementException with correct message when searchProducts has unavailable query`() =
        runTest {
            val query = "produtoInexistente"
            try {
                service.searchProducts(query).first()
                fail("NoSuchElementException esperada mas não lançada.")
            } catch (e: NoSuchElementException) {
                assertEquals("Não encontramos resultados para: $query", e.message)
                verify { Log.w("MockApiService", "Termo de busca não disponível: $query") }
            }
        }

    @Test(expected = IOException::class)
    fun `should throw IOException when JSON file for query is not found`() = runTest {
        val query = "camisa"
        val fileName = "search-MLA-$query.json"

        every {
            JsonUtils.getJsonDataFromAsset(
                mockContext,
                fileName
            )
        } returns null

        try {
            service.searchProducts(query).toList()
        } catch (e: IOException) {
            assertTrue(e.message?.startsWith("Arquivo não encontrado:") == true)
            verify { Log.e("MockApiService", "Erro ao ler arquivo JSON: ${e.message}") }
            throw e
        }
        fail("Deveria ter lançado IOException")
    }

    @Test(expected = JsonSyntaxException::class)
    fun `should throw JsonSyntaxException when JSON file is malformed`() = runTest {
        val query = "iphone"
        val fileName = "search-MLA-$query.json"
        val malformedJson =
            "{ query: 'iphone', results: [\"iPhone 13\" }"

        every { JsonUtils.getJsonDataFromAsset(mockContext, fileName) } returns malformedJson
        try {
            service.searchProducts(query).toList()
        } catch (e: JsonSyntaxException) {
            verify {
                Log.e(
                    eq("MockApiService"),
                    match { msg -> msg.startsWith("Erro ao fazer parse do JSON:") })
            }
            throw e
        }
        fail("Deveria ter lançado JsonSyntaxException")
    }

    @Test
    fun `should throw JsonSyntaxException and log error when JSON is malformed`() = runTest {
        val query = "zapatillas"
        val fileName = "search-MLA-$query.json"
        val malformedJson = """{"results": [1,2}"""

        every { JsonUtils.getJsonDataFromAsset(mockContext, fileName) } returns malformedJson

        try {
            service.searchProducts(query).first()
            fail("JsonSyntaxException esperada mas não lançada.")
        } catch (e: JsonSyntaxException) {
            assertNotNull(e.message)
            verify { Log.e("MockApiService", "Erro ao fazer parse do JSON: ${e.message}") }
        }
    }
}
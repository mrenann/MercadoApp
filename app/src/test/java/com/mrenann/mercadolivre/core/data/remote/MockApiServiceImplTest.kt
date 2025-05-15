package com.mrenann.mercadolivre.core.data.remote

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.mrenann.mercadolivre.core.data.remote.model.arrozString
import com.mrenann.mercadolivre.core.data.remote.model.iphoneCategoryString
import com.mrenann.mercadolivre.core.data.remote.model.iphoneDescriptionString
import com.mrenann.mercadolivre.core.data.remote.model.iphoneDetailsString
import com.mrenann.mercadolivre.core.data.remote.response.ItemCategoryResponse
import com.mrenann.mercadolivre.core.data.remote.response.ItemDescriptionResponse
import com.mrenann.mercadolivre.core.data.remote.response.ItemDetailsResponse
import com.mrenann.mercadolivre.core.data.remote.response.SearchQueryResponse
import com.mrenann.mercadolivre.core.utils.JsonUtils
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import io.mockk.verify
import kotlinx.coroutines.flow.catch
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
            val query = "produtoinexistente"
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
            val query = "produtoinexistente"
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

    @Test
    fun `should return ItemDetailsResponse when getItem is called with a valid item ID`() =
        runTest {
            val itemId = "MLA2005705454"
            val fileName = "item-$itemId.json"

            every {
                JsonUtils.getJsonDataFromAsset(mockContext, fileName)
            } returns iphoneDetailsString

            val expected = gson.fromJson(iphoneDetailsString, ItemDetailsResponse::class.java)
            val result = service.getItem(itemId).first()

            assertNotNull(result)

            assertEquals(expected.id, result.id)
            assertEquals(expected.title, result.title)

            verify { JsonUtils.getJsonDataFromAsset(mockContext, fileName) }
        }


    @Test
    fun `should throw IllegalArgumentException when getItem is called with an invalid item ID format`() =
        runTest {
            val invalidItemId = "INVALID123"
            try {
                service.getItem(invalidItemId).first()
            } catch (e: IllegalArgumentException) {
                assertEquals(
                    "O ID do item deve começar com MLB ou MLA",
                    e.message
                )
            }
        }

    @Test
    fun `should throw IOException when getItem is called and JSON file is not found`() {
        runTest {
            val itemId = "MLA123456789"
            val fileName = "item-$itemId.json"

            every { JsonUtils.getJsonDataFromAsset(mockContext, fileName) } returns null

            try {
                service.getItem(itemId).first()
                fail("IOException esperada mas não lançada.")
            } catch (e: IOException) {
                assertTrue(e.message?.startsWith("Arquivo não encontrado:") == true)
                verify { Log.e("MockApiService", "Erro ao ler arquivo JSON: ${e.message}") }
            }
        }
    }

    @Test
    fun `should throw JsonSyntaxException when getItem is called and JSON is malformed`() {
        runTest {
            val itemId = "MLB987654321"
            val fileName = "item-$itemId.json"
            val malformedJson = """{"id": "$itemId", "title": "Item Malformado" """

            every { JsonUtils.getJsonDataFromAsset(mockContext, fileName) } returns malformedJson

            try {
                service.getItem(itemId).first()
                fail("JsonSyntaxException esperada mas não lançada.")
            } catch (e: JsonSyntaxException) {
                verify { Log.e("MockApiService", "Erro ao fazer parse do JSON: ${e.message}") }
            }
        }
    }

    @Test
    fun `should return ItemCategoryResponse when getItemCategory is called with a valid item ID`() =
        runTest {
            val categoryId = "MLA2005705454"
            val fileName = "item-$categoryId-category.json"

            every {
                JsonUtils.getJsonDataFromAsset(
                    mockContext,
                    fileName
                )
            } returns iphoneCategoryString

            val expected = gson.fromJson(iphoneCategoryString, ItemCategoryResponse::class.java)
            val result = service.getItemCategory(categoryId).first()

            assertNotNull(result)
            assertEquals(expected.id, result.id)
            assertEquals(expected.name, result.name)
            assertEquals(expected.permalink, result.permalink)

            verify { JsonUtils.getJsonDataFromAsset(mockContext, fileName) }
        }

    @Test
    fun `should throw IllegalArgumentException when getItemCategory is called with an invalid item ID format`() =
        runTest {
            val invalidCategoryId = "INVALID123"
            try {
                service.getItemCategory(invalidCategoryId).first()
                fail("IllegalArgumentException esperada mas não lançada.")
            } catch (e: IllegalArgumentException) {
                assertEquals("O ID do item deve começar com MLB ou MLA", e.message)
            }
        }

    @Test
    fun `should throw IOException when getItemCategory is called and JSON file is not found`() =
        runTest {
            val categoryId = "MLA987654"
            val fileName = "category-$categoryId.json"

            every { JsonUtils.getJsonDataFromAsset(mockContext, fileName) } returns null

            try {
                service.getItemCategory(categoryId).first()
                fail("IOException esperada mas não lançada.")
            } catch (e: IOException) {
                assertTrue(e.message?.startsWith("Arquivo não encontrado:") == true)
                verify { Log.e("MockApiService", "Erro ao ler arquivo JSON: ${e.message}") }
            }
        }

    @Test
    fun `should throw JsonSyntaxException when getItemCategory is called and JSON is malformed`() =
        runTest {
            val categoryId = "MLA2005705454"
            val fileName = "item-$categoryId-category.json"
            val malformedJson = """{"id": "$categoryId", "name": "Categoria Malformada" """

            every { JsonUtils.getJsonDataFromAsset(mockContext, fileName) } returns malformedJson

            try {
                service.getItemCategory(categoryId).first()
                fail("JsonSyntaxException esperada mas não lançada.")
            } catch (e: JsonSyntaxException) {
                verify { Log.e("MockApiService", "Erro ao fazer parse do JSON: ${e.message}") }
            }
        }

    @Test
    fun `should return ItemDescriptionResponse when getItemDescription is called with a valid item ID`() {
        runTest {
            val itemId = "MLB1234567890"
            val fileName = "item-$itemId-description.json"

            every {
                JsonUtils.getJsonDataFromAsset(mockContext, fileName)
            } returns iphoneDescriptionString

            val expected =
                gson.fromJson(iphoneDescriptionString, ItemDescriptionResponse::class.java)
            val result = service.getItemDescription(itemId).first()

            assertNotNull(result)
            assertEquals(expected.dateCreated, result.dateCreated)
            assertEquals(expected.text, result.text)

            verify { JsonUtils.getJsonDataFromAsset(mockContext, fileName) }
        }

    }

    @Test
    fun `should throw IllegalArgumentException when getItem is called with an empty item ID`() =
        runTest {
            service.getItem("")
                .catch { e -> assertEquals("ID do item não pode ser vazio", e.message) }
                .collect {}
        }


    @Test
    fun `should throw IllegalArgumentException when getItemDescription is called with an invalid item ID format`() {
        runTest {
            val invalidItemId = "INVALID123"
            service.getItemDescription(invalidItemId)
                .catch { e -> assertEquals("O ID do item deve começar com MLB ou MLA", e.message) }
                .collect {}
        }
    }

    @Test
    fun `getItemDescription when JSON file is not found should throw IOException`() {
        runTest {
            val itemId = "MLA123456789"
            val fileName = "item-$itemId-description.json"

            every { JsonUtils.getJsonDataFromAsset(mockContext, fileName) } returns null

            try {
                service.getItemDescription(itemId).first()
                fail("IOException esperada mas não lançada.")
            } catch (e: IOException) {
                assertTrue(e.message?.startsWith("Arquivo não encontrado:") == true)
                verify { Log.e("MockApiService", "Erro ao ler arquivo JSON: ${e.message}") }
            }
        }

    }

    @Test
    fun `should throw JsonSyntaxException when getItemDescription is called and JSON is malformed`() {
        runTest {
            val itemId = "MLB987654321"
            val fileName = "item-$itemId-description.json"
            val malformedJson = """{"id": "$itemId", "text": "Descrição Malformada" """

            every { JsonUtils.getJsonDataFromAsset(mockContext, fileName) } returns malformedJson

            try {
                service.getItemDescription(itemId).first()
                fail("JsonSyntaxException esperada mas não lançada.")
            } catch (e: JsonSyntaxException) {
                verify { Log.e("MockApiService", "Erro ao fazer parse do JSON: ${e.message}") }
            }
        }

    }

    @Test
    fun `should throw IllegalArgumentException when getItemCategory is called with an empty item ID`() {
        runTest {
            val emptyCategoryId = ""
            service.getItemCategory(emptyCategoryId)
                .catch { e -> assertEquals("ID do item não pode ser vazio", e.message) }
                .collect {}
        }
    }

    @Test
    fun `getItemDescription when called with an empty item ID should throw IllegalArgumentException`() {
        runTest {
            service.getItemDescription("")
                .catch { e -> assertEquals("ID do item não pode ser vazio", e.message) }
                .collect {}
        }
    }
}
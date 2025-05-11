package com.mrenann.mercadolivre.core.data.remote

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.mrenann.mercadolivre.core.data.remote.response.SearchQueryResponse
import com.mrenann.mercadolivre.core.utils.JsonUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class MockApiServiceImpl(
    private val context: Context
) : MockApiService {

    private val gson = Gson()

    override fun searchProducts(query: String): Flow<SearchQueryResponse> = flow {

        try {
            validateSearch(query)
            val fileName = "search-MLA-$query.json"
            Log.d("MockApiService", "Buscando arquivo: $fileName")

            val jsonFromFile = JsonUtils.getJsonDataFromAsset(context, fileName)
                ?: throw IOException("Arquivo não encontrado: $fileName")

            val response = gson.fromJson(jsonFromFile, SearchQueryResponse::class.java)
            emit(response)

        } catch (e: IOException) {
            Log.e("MockApiService", "Erro ao ler arquivo JSON: ${e.message}")
            throw e
        } catch (e: JsonSyntaxException) {
            Log.e("MockApiService", "Erro ao fazer parse do JSON: ${e.message}")
            throw e
        }

    }

    private fun validateSearch(query: String) {
        val availableTerms = listOf("arroz", "cafe", "camisa", "iphone", "zapatillas")

        val normalizedQuery = query.trim().lowercase()

        if (normalizedQuery.isBlank()) {
            require(normalizedQuery.isNotBlank()) { "Termo de busca não pode ser vazio" }
        }

        if (normalizedQuery !in availableTerms) {
            Log.w("MockApiService", "Termo de busca não disponível: $query")
            throw NoSuchElementException("Não encontramos resultados para: $query")
        }
    }
}
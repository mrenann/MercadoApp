package com.mrenann.mercadolivre.searchScreen.presentation.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import com.mrenann.mercadolivre.core.data.remote.MockApiService
import com.mrenann.mercadolivre.core.data.remote.response.SearchQueryResponse
import com.mrenann.mercadolivre.searchScreen.presentation.components.ItemCard
import kotlinx.coroutines.flow.first
import org.koin.compose.koinInject

data class ResultsSearchScreen(
    val query: String,
) : Screen {
    @Composable
    override fun Content() {
        val api = koinInject<MockApiService>()
        var response by remember { mutableStateOf<SearchQueryResponse?>(null) }
        LaunchedEffect(Unit) {
            response = api.searchProducts(query).first()
        }
        LazyColumn {
            items(response?.results?.size ?: 0) { index ->
                val item = response?.results?.get(index)
                item?.let {
                    ItemCard(
                        item = item,
                        modifier = Modifier,
                    )
                }
            }
        }
    }
}

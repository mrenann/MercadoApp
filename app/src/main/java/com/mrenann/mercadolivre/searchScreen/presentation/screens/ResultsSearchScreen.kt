package com.mrenann.mercadolivre.searchScreen.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.mrenann.mercadolivre.core.presentation.components.ErrorView
import com.mrenann.mercadolivre.core.presentation.components.LoadingView
import com.mrenann.mercadolivre.homeScreen.presentation.components.Header
import com.mrenann.mercadolivre.searchScreen.presentation.screenModel.SearchScreenModel

data class ResultsSearchScreen(
    val query: String,
) : Screen {
    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<SearchScreenModel>()
        val state by screenModel.state.collectAsState()
        val navigator = LocalNavigator.currentOrThrow
        LaunchedEffect(Unit) {
            screenModel.searchProducts(query)
        }

        Column {
            Header(
                query = query,
                onBackClick = { navigator.pop() },
                onSearchClick = {
                    navigator.replace(
                        SearchScreen()
                    )
                }
            )
            when (state) {
                is SearchScreenModel.State.Error -> {
                    ErrorView(
                        message = (state as SearchScreenModel.State.Error).message,
                        buttonText = "Voltar pro ínicio",
                        onButtonClick = { navigator.pop() },
                    )
                }

                SearchScreenModel.State.Init -> Text("init")
                SearchScreenModel.State.Loading -> LoadingView()
                is SearchScreenModel.State.Result -> {
                    Text("Result")
                }
            }
        }

    }
}

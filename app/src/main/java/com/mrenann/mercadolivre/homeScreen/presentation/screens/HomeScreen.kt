package com.mrenann.mercadolivre.homeScreen.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.mrenann.mercadolivre.core.presentation.components.Header
import com.mrenann.mercadolivre.homeScreen.presentation.components.HomeContent
import com.mrenann.mercadolivre.homeScreen.presentation.components.HomeLoading
import com.mrenann.mercadolivre.homeScreen.presentation.screenModel.HomeScreenModel
import com.mrenann.mercadolivre.searchScreen.presentation.screens.SearchScreen

class HomeScreen : Screen {
    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<HomeScreenModel>()
        val state by screenModel.state.collectAsState()
        val navigator = LocalNavigator.currentOrThrow

        Header(
            onSearchClick = { navigator.push(SearchScreen()) }
        )

        when (state) {
            is HomeScreenModel.State.Result -> HomeContent()
            HomeScreenModel.State.Loading -> HomeLoading()
        }

    }
}

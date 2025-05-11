package com.mrenann.mercadolivre.homeScreen.presentation.screens

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.mrenann.mercadolivre.homeScreen.presentation.components.Header
import com.mrenann.mercadolivre.searchScreen.presentation.screens.SearchScreen

class HomeScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Header(
            { navigator.push(SearchScreen()) },
        )
    }
}

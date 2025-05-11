package com.mrenann.mercadolivre.detailsScreen.presentation.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.mrenann.mercadolivre.core.data.remote.model.Result

class DetailsScreen(item: Result) : Screen {
    @Composable
    override fun Content() {
        Text("DETALHES")
    }
}
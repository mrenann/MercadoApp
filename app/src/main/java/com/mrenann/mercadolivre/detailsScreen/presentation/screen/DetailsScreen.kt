package com.mrenann.mercadolivre.detailsScreen.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import coil3.compose.AsyncImage
import com.mrenann.mercadolivre.core.data.remote.model.Result
import com.mrenann.mercadolivre.core.utils.forceHttps

class DetailsScreen(val item: Result) : Screen {
    @Composable
    override fun Content() {
        Column {
            Text("${item.title}")
            AsyncImage(
                model = item.thumbnail?.forceHttps(),
                contentDescription = item.title ?: "Sem Título",
                modifier =
                    Modifier
                        .size(150.dp)
                        .background(Color.LightGray),
                contentScale = ContentScale.Fit,
            )
        }
    }
}
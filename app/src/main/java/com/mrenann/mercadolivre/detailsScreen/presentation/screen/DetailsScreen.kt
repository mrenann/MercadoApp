package com.mrenann.mercadolivre.detailsScreen.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.mrenann.mercadolivre.core.data.remote.model.search.Result
import com.mrenann.mercadolivre.detailsScreen.presentation.components.LoadingDetails
import com.mrenann.mercadolivre.detailsScreen.presentation.components.ProductDetailsContent
import com.mrenann.mercadolivre.detailsScreen.presentation.screenModel.DetailsScreenModel
import com.mrenann.mercadolivre.ui.theme.YellowAccent
import compose.icons.EvaIcons
import compose.icons.evaicons.Outline
import compose.icons.evaicons.outline.ChevronLeft

class DetailsScreen(val item: Result) : Screen {
    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<DetailsScreenModel>()
        val state by screenModel.state.collectAsState()
        val navigator = LocalNavigator.currentOrThrow

        val productId = remember { item.id ?: "" }
        LaunchedEffect(productId) {
            if (state !is DetailsScreenModel.State.Result &&
                state !is DetailsScreenModel.State.Error
            ) {
                screenModel.getProduct(productId)
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(YellowAccent)
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { navigator.pop() },
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    Icon(
                        imageVector = EvaIcons.Outline.ChevronLeft,
                        contentDescription = "Botão de voltar",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }

            when (val currentState = state) {
                is DetailsScreenModel.State.Loading -> {
                    LoadingDetails()
                }

                is DetailsScreenModel.State.Error -> {
                    Text("error")
                }

                is DetailsScreenModel.State.Result -> {
                    ProductDetailsContent(
                        state = currentState,
                        onImageClick = { images, initialPage ->
                            navigator.push(
                                ImageDetailsScreen(
                                    images = images,
                                    initialPage = initialPage
                                )
                            )
                        }
                    )
                }
            }
        }
    }
}
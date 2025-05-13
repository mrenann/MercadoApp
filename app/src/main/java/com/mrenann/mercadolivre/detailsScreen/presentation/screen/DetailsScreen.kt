package com.mrenann.mercadolivre.detailsScreen.presentation.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.mrenann.mercadolivre.core.domain.model.SearchResult
import com.mrenann.mercadolivre.detailsScreen.presentation.components.ItemDetailsContent
import com.mrenann.mercadolivre.detailsScreen.presentation.components.LoadingDetails
import com.mrenann.mercadolivre.detailsScreen.presentation.screenModel.DetailsScreenModel
import com.mrenann.mercadolivre.ui.theme.YellowAccent
import compose.icons.EvaIcons
import compose.icons.evaicons.Outline
import compose.icons.evaicons.outline.ChevronLeft

class DetailsScreen(val item: SearchResult) : Screen {
    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<DetailsScreenModel>()
        val state by screenModel.state.collectAsState()
        val navigator = LocalNavigator.currentOrThrow

        val isHeaderVisible = rememberSaveable { mutableStateOf(true) }
        val scrollState = rememberLazyListState()
        val previousScrollInfo = remember { mutableStateOf(Pair(0, 0)) }

        val shouldShowHeader by remember {
            derivedStateOf {
                val firstVisibleItemIndex = scrollState.firstVisibleItemIndex
                val firstVisibleItemScrollOffset = scrollState.firstVisibleItemScrollOffset

                val currentScrollInfo = Pair(firstVisibleItemIndex, firstVisibleItemScrollOffset)
                val isScrollingDown =
                    if (currentScrollInfo.first == previousScrollInfo.value.first) {
                        currentScrollInfo.second > previousScrollInfo.value.second
                    } else {
                        currentScrollInfo.first > previousScrollInfo.value.first
                    }

                previousScrollInfo.value = currentScrollInfo
                !isScrollingDown
            }
        }

        LaunchedEffect(shouldShowHeader) {
            isHeaderVisible.value = shouldShowHeader
        }

        val productId = remember { item.id ?: "" }
        LaunchedEffect(productId) {
            if (state !is DetailsScreenModel.State.Result &&
                state !is DetailsScreenModel.State.Error
            ) {
                screenModel.getProduct(productId)
            }
        }

        Box(modifier = Modifier.fillMaxSize()) {
            AnimatedVisibility(
                visible = isHeaderVisible.value,
                modifier = Modifier
                    .fillMaxWidth()
                    .zIndex(1f),
                enter = slideInVertically(
                    initialOffsetY = { -it },
                    animationSpec = tween(durationMillis = 100)
                ),
                exit = slideOutVertically(
                    targetOffsetY = { -it },
                    animationSpec = tween(durationMillis = 100)
                )
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
                            tint = Color.Black
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(top = if (isHeaderVisible.value) 60.dp else 0.dp)
            ) {
                when (val currentState = state) {
                    is DetailsScreenModel.State.Loading -> {
                        LoadingDetails(
                            item = item,
                            onImageClick = { images, initialPage ->
                                navigator.push(
                                    ImageDetailsScreen(
                                        images = images,
                                        initialPage = initialPage
                                    )
                                )
                            },
                            scrollState = scrollState
                        )
                    }

                    is DetailsScreenModel.State.Error -> {
                        Text("error")
                    }

                    is DetailsScreenModel.State.Result -> {
                        ItemDetailsContent(
                            item = currentState.product,
                            fallback = item,
                            onImageClick = { images, initialPage ->
                                navigator.push(
                                    ImageDetailsScreen(
                                        images = images,
                                        initialPage = initialPage
                                    )
                                )
                            },
                            scrollState = scrollState
                        )
                    }
                }
            }
        }
    }
}
package com.mrenann.mercadolivre.detailsScreen.presentation.screens

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import cafe.adriel.lyricist.strings
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

class DetailsScreen(val item: SearchResult, val query: String) : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<DetailsScreenModel>()
        val state by screenModel.state.collectAsState()
        val navigator = LocalNavigator.currentOrThrow
        val scrollState = rememberLazyListState()

        val productId = remember { item.id.orEmpty() }

        LaunchedEffect(productId) {
            if (state !is DetailsScreenModel.State.Result &&
                state !is DetailsScreenModel.State.Error
            ) {
                screenModel.getProduct(productId)
            }
        }

        val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = query,
                            color = Color.Black
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { navigator.pop() }) {
                            Icon(
                                imageVector = EvaIcons.Outline.ChevronLeft,
                                contentDescription = strings.detailsStrings.backIconDescription,
                                tint = Color.Black
                            )
                        }
                    },
                    windowInsets = WindowInsets(
                        left = 0.dp,
                        top = 0.dp,
                        right = 0.dp,
                        bottom = 0.dp
                    ),
                    expandedHeight = 50.dp,
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = YellowAccent,
                        scrolledContainerColor = YellowAccent
                    ),
                    scrollBehavior = scrollBehavior
                )
            },
            containerColor = MaterialTheme.colorScheme.background
        ) { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding),
                state = scrollState
            ) {
                item {
                    when (val currentState = state) {
                        is DetailsScreenModel.State.Loading -> {
                            LoadingDetails(
                                item = item,
                                onImageClick = { images, initialPage ->
                                    navigator.push(
                                        ImageDetailsScreen(images, initialPage)
                                    )
                                }
                            )
                        }

                        is DetailsScreenModel.State.Error -> {
                            Text(strings.detailsStrings.loadDataError)
                        }

                        is DetailsScreenModel.State.Result -> {
                            ItemDetailsContent(
                                query = query,
                                item = currentState.product,
                                fallback = item,
                                onImageClick = { images, initialPage ->
                                    navigator.push(
                                        ImageDetailsScreen(images, initialPage)
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }


}
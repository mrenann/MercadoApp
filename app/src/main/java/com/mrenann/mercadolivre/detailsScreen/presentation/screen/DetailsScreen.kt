package com.mrenann.mercadolivre.detailsScreen.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.mrenann.mercadolivre.core.data.remote.model.search.Result
import com.mrenann.mercadolivre.core.utils.Resource
import com.mrenann.mercadolivre.core.utils.forceHttps
import com.mrenann.mercadolivre.detailsScreen.presentation.components.LoadingDetails
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
        LaunchedEffect(Unit) {
            screenModel.getProduct(item.id ?: "")
        }

        Column {
            Column(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .background(
                            color = YellowAccent,
                        )
                        .padding(all = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                IconButton(onClick = {
                    navigator.pop()
                }) {
                    Icon(
                        imageVector = EvaIcons.Outline.ChevronLeft,
                        contentDescription = "Back",
                    )
                }
            }

            LoadingDetails()
            when (state) {
                is DetailsScreenModel.State.Error -> Text("Error")
                DetailsScreenModel.State.Loading -> Text("Loading")
                is DetailsScreenModel.State.Result -> {
                    val product = (state as DetailsScreenModel.State.Result).product

                    when (val item = product.item) {
                        is Resource.Success -> {
                            Column {
                                val imagesSize = item.data.pictures?.size ?: 0
                                val pagerState = rememberPagerState(
                                    initialPage = 0,
                                    pageCount = { imagesSize }
                                )

                                Box {
                                    HorizontalPager(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .fillMaxHeight(fraction = 0.35F)
                                            .background(Color.White),
                                        state = pagerState,
                                        key = {
                                            item.data.pictures?.get(it)?.url?.forceHttps() ?: ""
                                        }
                                    ) { index ->

                                        AsyncImage(
                                            model = ImageRequest.Builder(LocalContext.current)
                                                .data(item.data.pictures?.get(index)?.url?.forceHttps())
                                                .crossfade(true)
                                                .build(),
                                            contentDescription = "Product Image",
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .clickable {
                                                    navigator.push(
                                                        ImageDetailsScreen(
                                                            images = item.data.pictures
                                                                ?: emptyList()
                                                        )
                                                    )
                                                },
                                            contentScale = ContentScale.Fit,
                                        )

                                    }

                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically,
                                    ) {
                                        Text(
                                            text = "${pagerState.currentPage + 1} / $imagesSize",
                                            modifier = Modifier
                                                .padding(6.dp)
                                                .background(Color.LightGray, CircleShape)
                                                .padding(horizontal = 4.dp)
                                        )
                                    }
                                    Row(
                                        modifier = Modifier
                                            .align(Alignment.BottomCenter)
                                            .padding(bottom = 4.dp),
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        item.data.pictures?.indices?.forEach { index ->
                                            val isSelected = pagerState.currentPage == index
                                            Box(
                                                modifier = Modifier
                                                    .padding(4.dp)
                                                    .size(6.dp)
                                                    .background(
                                                        color = if (isSelected) Color.Blue else Color.LightGray,
                                                        CircleShape
                                                    )
                                            )
                                        }
                                    }


                                }
                            }
                        }

                        is Resource.Loading -> {}
                        is Resource.Error -> {}
                    }


                }
            }

        }


    }
}
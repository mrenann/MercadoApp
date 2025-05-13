package com.mrenann.mercadolivre.detailsScreen.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import compose.icons.EvaIcons
import compose.icons.evaicons.Outline
import compose.icons.evaicons.outline.ChevronLeft
import net.engawapg.lib.zoomable.ZoomState
import net.engawapg.lib.zoomable.zoomable

data class ImageDetailsScreen(val images: List<String>, val initialPage: Int = 0) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val pagerState = rememberPagerState(
            initialPage = initialPage,
            pageCount = { images.size }
        )
        val currentPage = pagerState.currentPage
        var zoomStates by rememberSaveable(images) {
            mutableStateOf(images.map {
                ZoomState(
                    initialScale = 1f,
                    maxScale = 5f
                )
            })
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(color = 0xFFF1F1F1)),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black),
            ) {

                HorizontalPager(
                    modifier = Modifier.fillMaxSize(),
                    state = pagerState,
                    key = { images[it] }
                ) { index ->

                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(images[index])
                            .crossfade(true)
                            .build(),
                        contentDescription = "produto imagem",
                        modifier = Modifier
                            .fillMaxSize()
                            .zoomable(zoomState = zoomStates[index]),
                        contentScale = ContentScale.Fit,
                    )

                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(color = 0x31000000)),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    IconButton(onClick = {
                        navigator.pop()
                    }) {
                        Icon(
                            tint = Color.White,
                            imageVector = EvaIcons.Outline.ChevronLeft,
                            contentDescription = "",
                        )
                    }

                    Text(
                        modifier = Modifier.padding(horizontal = 12.dp),
                        text = "${currentPage + 1}/${images.size}",
                        color = Color.White
                    )
                }
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    images.indices.forEach { index ->
                        val isSelected = pagerState.currentPage == index
                        Box(
                            modifier = Modifier
                                .padding(4.dp)
                                .size(if (isSelected) 12.dp else 8.dp)
                                .background(
                                    color = if (isSelected) Color.White else Color.Gray,
                                    shape = CircleShape
                                )
                        )
                    }
                }
            }

        }

    }
}

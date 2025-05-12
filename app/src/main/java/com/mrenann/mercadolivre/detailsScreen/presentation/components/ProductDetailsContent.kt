package com.mrenann.mercadolivre.detailsScreen.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.mrenann.mercadolivre.core.data.remote.model.details.Picture
import com.mrenann.mercadolivre.core.utils.Resource
import com.mrenann.mercadolivre.core.utils.forceHttps
import com.mrenann.mercadolivre.detailsScreen.presentation.screenModel.DetailsScreenModel

@Composable
fun ProductDetailsContent(
    state: DetailsScreenModel.State.Result,
    onImageClick: (List<Picture>, Int) -> Unit
) {
    when (val item = state.product.item) {
        is Resource.Success -> {
            val pictures = item.data.pictures ?: emptyList()
            val imagesSize = pictures.size

            val pagerState = rememberPagerState(
                initialPage = 0,
                pageCount = { imagesSize }
            )

            Box(modifier = Modifier.fillMaxWidth()) {
                HorizontalPager(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(fraction = 0.35F)
                        .background(Color.White),
                    state = pagerState,
                    key = { index -> pictures[index].url?.forceHttps() ?: "" }
                ) { index ->
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(pictures[index].url?.forceHttps() ?: "")
                            .crossfade(true)
                            .build(),
                        contentDescription = "Imagem",
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                onImageClick(pictures, pagerState.currentPage)
                            },
                        contentScale = ContentScale.Fit,
                    )
                }

                Text(
                    text = "${pagerState.currentPage + 1} / $imagesSize",
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(6.dp)
                        .background(Color.LightGray.copy(alpha = 0.7f), CircleShape)
                        .padding(horizontal = 4.dp),
                    style = MaterialTheme.typography.bodySmall
                )

                Row(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 4.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    pictures.indices.forEach { index ->
                        val isSelected = pagerState.currentPage == index
                        Box(
                            modifier = Modifier
                                .padding(4.dp)
                                .size(6.dp)
                                .background(
                                    color = if (isSelected) Color.Blue else Color.LightGray,
                                    shape = CircleShape
                                )
                        )
                    }
                }
            }
        }

        is Resource.Loading -> LoadingDetails()
        is Resource.Error -> Text("ERROR")
    }
}
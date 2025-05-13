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

@Composable
fun ImagesPager(
    imagesSize: Int,
    pictures: List<String>,
    onImageClick: (List<String>, Int) -> Unit
) {
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
            key = { index -> pictures[index] }
        ) { index ->
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(pictures[index])
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

        if (imagesSize > 1) {
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 4.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                pictures.indices.forEach { index ->
                    val isSelected = pagerState.currentPage == index
                    val closeToSelected =
                        (index >= pagerState.currentPage - 2) && index <= pagerState.currentPage + 2
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .size(if (closeToSelected) 6.dp else 4.dp)
                            .background(
                                color = if (isSelected) Color.Blue else Color.LightGray,
                                shape = CircleShape
                            )
                    )
                }
            }
        }
    }
}
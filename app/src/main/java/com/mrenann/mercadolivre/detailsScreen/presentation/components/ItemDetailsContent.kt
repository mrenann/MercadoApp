package com.mrenann.mercadolivre.detailsScreen.presentation.components

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.mrenann.mercadolivre.core.data.remote.model.details.Picture
import com.mrenann.mercadolivre.core.data.remote.response.ItemDetailsResponse
import com.mrenann.mercadolivre.core.utils.Resource
import com.mrenann.mercadolivre.core.utils.forceHttps
import com.mrenann.mercadolivre.detailsScreen.domain.utils.Constants.Fifty
import com.mrenann.mercadolivre.detailsScreen.domain.utils.Constants.One
import com.mrenann.mercadolivre.detailsScreen.domain.utils.Constants.Ten
import com.mrenann.mercadolivre.detailsScreen.utils.toCondition

@Composable
fun ItemDetailsContent(
    item: Resource<ItemDetailsResponse>,
    onImageClick: (List<Picture>, Int) -> Unit
) {
    Column {
        when (item) {
            is Resource.Success -> {
                val product = item.data
                val pictures = product.pictures ?: emptyList()
                val imagesSize = pictures.size

                val pagerState = rememberPagerState(
                    initialPage = 0,
                    pageCount = { imagesSize }
                )

                Column(
                    modifier = Modifier
                        .padding(all = 12.dp)
                ) {
                    Text(
                        text = "${product.condition?.toCondition()} | +${Ten}mil vendidos",
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                    Text("${product.title}")
                }
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

                PriceDisplay(
                    modifier = Modifier.padding(horizontal = 12.dp),
                    originalPrice = product.originalPrice,
                    currentPrice = product.price ?: 0.0,
                    currency = product.currencyId ?: "BRL",
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 12.dp)
                        .background(
                            color = Color(color = 0xFFF5F5F5),
                            shape = RoundedCornerShape(size = 8.dp)
                        )
                        .padding(all = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(space = 16.dp)
                ) {
                    Text(text = "Quantidade: ${One}")
                    val availableQuantityText =
                        if ((product.initialQuantity ?: 0) < Fifty) {
                            "${product.initialQuantity}"
                        } else {
                            "+50"
                        }
                    Text(text = "($availableQuantityText Disponíveis)", color = Color.Gray)
                }
            }

            is Resource.Loading -> LoadingDetails()
            is Resource.Error -> Text("ERROR")
        }
    }
}
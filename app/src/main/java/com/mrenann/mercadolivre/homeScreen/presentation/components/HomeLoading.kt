package com.mrenann.mercadolivre.homeScreen.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mrenann.mercadolivre.core.utils.skeletonColor

@Composable
fun HomeLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(color =0xFFF0F0F0))
    ) {
        LazyColumn {
            item {
                BannerContainer {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .background((Color.White), RoundedCornerShape(4.dp))
                    ) {
                        Box(
                            modifier = Modifier
                                .width(180.dp)
                                .height(16.dp)
                                .background(brush = skeletonColor, RoundedCornerShape(4.dp))
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Box(
                            modifier = Modifier
                                .width(120.dp)
                                .height(16.dp)
                                .background(brush = skeletonColor, RoundedCornerShape(4.dp))
                        )
                    }
                }
            }

            item {
                HomeContentLoading()
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun HomeLoadingPreview() {
    Surface {
        HomeLoading()
    }
}
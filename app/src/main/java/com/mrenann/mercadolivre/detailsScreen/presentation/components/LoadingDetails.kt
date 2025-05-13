package com.mrenann.mercadolivre.detailsScreen.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mrenann.mercadolivre.core.domain.model.SearchResult


@Composable
fun LoadingDetails(
    item: SearchResult,
    onImageClick: (List<String>, Int) -> Unit,
    scrollState: LazyListState
) {

    LazyColumn(
        state = scrollState,
    ) {
        item {
            Column(
                modifier = Modifier
                    .padding(all = 12.dp)
            ) {
                Text("${item.title}")
                BoxShimmer(
                    fraction = .6F,
                    height = 16.dp
                )
            }

            ImagesPager(1, listOf(item.thumbnail ?: ""), onImageClick)

            PriceDisplay(
                modifier = Modifier.padding(horizontal = 12.dp),
                originalPrice = item.originalPrice,
                currentPrice = item.price ?: 0.0,
                currency = item.currencyId ?: "BRL",
            )

            Column(
                modifier = Modifier.padding(vertical = 12.dp, horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(space = 12.dp)
            ) {
                TopicBoxShimmer()
                TopicBoxShimmer()

                Spacer(modifier = Modifier.height(12.dp))
                BoxShimmer(
                    fraction = .5F,
                    height = 16.dp
                )
            }
        }

    }
}
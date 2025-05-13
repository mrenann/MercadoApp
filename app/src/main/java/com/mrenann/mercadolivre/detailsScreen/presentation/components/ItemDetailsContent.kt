package com.mrenann.mercadolivre.detailsScreen.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mrenann.mercadolivre.core.domain.model.DetailedProduct
import com.mrenann.mercadolivre.core.domain.model.SearchResult
import com.mrenann.mercadolivre.detailsScreen.domain.utils.Constants.Ten
import com.mrenann.mercadolivre.detailsScreen.utils.toCondition

@Composable
fun ItemDetailsContent(
    item: DetailedProduct,
    onImageClick: (List<String>, Int) -> Unit,
    fallback: SearchResult,
    scrollState: LazyListState
) {
    LazyColumn(
        state = scrollState,
    ) {
        val product = item.item
        val pictures = product?.pictures
            ?: if (fallback.thumbnail != null) listOf(fallback.thumbnail) else emptyList()
        val imagesSize = pictures.size
        val originalPrice = product?.originalPrice
            ?: if (product == null) fallback.originalPrice else null
        val currentPrice = product?.price
            ?: fallback.price ?: 0.0
        val currency = product?.currencyId ?: fallback.currencyId ?: "BRL"

        item {
            Column(
                modifier = Modifier
                    .padding(all = 12.dp)
            ) {
                product?.condition?.let {
                    Text(
                        text = "${product.condition.toCondition()} | +${Ten}mil vendidos",
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                }
                Text(product?.title ?: fallback.title ?: "Sem título")
            }

            ImagesPager(
                imagesSize,
                pictures,
                onImageClick
            )

            PriceDisplay(
                modifier = Modifier.padding(horizontal = 12.dp),
                originalPrice = originalPrice,
                currentPrice = currentPrice,
                currency = currency,
            )

            product?.let {
                DetailsContent(product)
            }

            DescriptionContent(
                description = item.description ?: "Sem descrição disponível"
            )
        }
    }
}
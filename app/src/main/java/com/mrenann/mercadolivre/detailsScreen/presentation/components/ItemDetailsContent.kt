package com.mrenann.mercadolivre.detailsScreen.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.lyricist.strings
import com.mrenann.mercadolivre.core.domain.model.DetailedProduct
import com.mrenann.mercadolivre.core.domain.model.SearchResult
import com.mrenann.mercadolivre.detailsScreen.utils.toCondition

@Composable
fun ItemDetailsContent(
    item: DetailedProduct,
    query: String = "",
    onImageClick: (List<String>, Int) -> Unit,
    fallback: SearchResult,
) {
    val strings = strings.detailsStrings
    Column {
        val product = item.item
        val pictures = product?.pictures
            ?: if (fallback.thumbnail != null) listOf(fallback.thumbnail) else emptyList()
        val imagesSize = pictures.size
        val originalPrice = product?.originalPrice
            ?: if (product == null) fallback.originalPrice else null
        val currentPrice = product?.price
            ?: fallback.price ?: 0.0
        val currency = product?.currencyId ?: fallback.currencyId ?: strings.currencyDefault

        CategoryContent(
            category = item.category,
            query = query
        )

        Column(
            modifier = Modifier
                .padding(all = 12.dp)
        ) {
            product?.condition?.let {
                Text(
                    text = strings.productCondition(
                        it.toCondition()
                    ),
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            }
            Text(product?.title ?: fallback.title ?: strings.withoutTitle)
        }

        ImagesPager(
            imagesSize,
            pictures,
            onImageClick
        )

        PriceDisplay(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
            originalPrice = originalPrice,
            currentPrice = currentPrice,
            currency = currency,
        )

        product?.let {
            DetailsContent(product)
        }

        DescriptionContent(
            description = item.description ?: strings.withoutDescription
        )
    }

}
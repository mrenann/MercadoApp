package com.mrenann.mercadolivre.detailsScreen.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.lyricist.strings
import com.mrenann.mercadolivre.core.domain.model.ProductDetails
import com.mrenann.mercadolivre.detailsScreen.domain.utils.Constants.Fifty

@Composable
fun DetailsContent(product: ProductDetails) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 12.dp)
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = RoundedCornerShape(size = 8.dp)
            )
            .padding(all = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(space = 16.dp)
    ) {
        Text(text = strings.detailsStrings.quantityOne)
        val availableQuantityText =
            if (product.quantity < Fifty) {
                product.quantity.toString()
            } else {
                strings.detailsStrings.moreFifty
            }
        Text(
            text = strings.detailsStrings.availableQuantity(availableQuantityText),
            color = Color.Gray
        )
    }
}
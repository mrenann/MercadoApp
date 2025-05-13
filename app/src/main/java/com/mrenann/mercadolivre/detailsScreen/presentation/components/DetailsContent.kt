package com.mrenann.mercadolivre.detailsScreen.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mrenann.mercadolivre.core.domain.model.ProductDetails
import com.mrenann.mercadolivre.detailsScreen.domain.utils.Constants.Fifty
import com.mrenann.mercadolivre.detailsScreen.domain.utils.Constants.One

@Composable
fun DetailsContent(product: ProductDetails) {
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
            if (product.quantity < Fifty) {
                "${product.quantity}"
            } else {
                "+50"
            }
        Text(text = "($availableQuantityText Disponíveis)", color = Color.Gray)
    }
}
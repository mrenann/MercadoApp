package com.mrenann.mercadolivre.detailsScreen.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.lyricist.strings
import com.mrenann.mercadolivre.core.utils.formatBalance
import com.mrenann.mercadolivre.detailsScreen.domain.utils.Constants.PerCent
import java.util.Locale

@Composable
fun PriceDisplay(
    originalPrice: Double?,
    currentPrice: Double,
    modifier: Modifier = Modifier,
    currency: String = "BRL",
) {
    val discountPercentage =
        if (originalPrice == null) null else ((originalPrice - currentPrice) / originalPrice * PerCent).toInt()

    val formattedCurrentPrice = String.format(Locale.US, "%.2f", currentPrice)
    val (currentIntPart, currentDecimalPart) = formattedCurrentPrice.split(".")

    Column(
        modifier = modifier,
    ) {
        originalPrice?.let {
            val formattedOriginalPrice = String.format(Locale.US, "%.2f", originalPrice)
            val (originalIntPart, originalDecimalPart) = formattedOriginalPrice.split(".")

            Row {
                Text(
                    text = originalIntPart.toInt().formatBalance(
                        showCurrency = true,
                        currency = currency,
                        withDecimals = false
                    ),
                    color = Color.Gray,
                    fontSize = 14.sp,
                    textDecoration = TextDecoration.LineThrough
                )
                Text(
                    text = originalDecimalPart.padStart(2, '0'),
                    color = Color.Gray,
                    fontSize = 10.sp,
                    textDecoration = TextDecoration.LineThrough,
                    modifier = Modifier.padding(start = 2.dp)
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row {
                Text(
                    text = currentIntPart.toInt().formatBalance(
                        showCurrency = true,
                        currency = currency,
                        withDecimals = false
                    ),
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = currentDecimalPart.padStart(2, '0'),
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 2.dp)
                )
            }

            discountPercentage?.let {
                Text(
                    text = strings.detailsStrings.discount(discountPercentage),
                    color = Color(color = 0xFF4CAF50),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}


@Preview
@Composable
fun PriceDisplayPreview() {
    PriceDisplay(
        originalPrice = 119.00,
        currentPrice = 118.13
    )
}
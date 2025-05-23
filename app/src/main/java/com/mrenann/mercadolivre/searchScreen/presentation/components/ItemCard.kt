package com.mrenann.mercadolivre.searchScreen.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.lyricist.strings
import coil3.compose.AsyncImage
import com.mrenann.mercadolivre.core.domain.model.SearchResult
import com.mrenann.mercadolivre.core.utils.formatBalance

@Composable
fun ItemCard(
    item: SearchResult,
    modifier: Modifier = Modifier,
    onItemClick: () -> Unit = {},
) {
    val strings = strings.searchStrings

    Card(
        modifier =
            modifier
                .fillMaxWidth()
                .clickable { onItemClick() },
        shape = RoundedCornerShape(0.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row {
                AsyncImage(
                    model = item.thumbnail,
                    contentDescription = item.title ?: strings.withoutTitle,
                    modifier =
                        Modifier
                            .size(150.dp)
                            .background(Color.LightGray),
                    contentScale = ContentScale.Fit,
                )

                Column(
                    modifier =
                        Modifier
                            .padding(start = 12.dp)
                            .weight(1f),
                ) {
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = item.title ?: strings.withoutTitle,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 2,
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    if (item.originalPrice != item.price && item.originalPrice != null) {
                        Text(

                            text = item.originalPrice.formatBalance(
                                currency = item.currencyId ?: strings.currencyDefault,
                            ),
                            fontSize = 13.sp,
                            color = Color.Gray,
                            textDecoration = TextDecoration.LineThrough,
                        )
                    }

                    Text(
                        text = (item.price ?: 0.0).formatBalance(
                            currency = item.currencyId ?: strings.currencyDefault,
                        ),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onBackground,
                    )

                    if (item.freeShipping == true) {
                        Text(
                            text = strings.freeShipping,
                            fontSize = 13.sp,
                            color = Color.Green,
                            fontWeight = FontWeight.Medium,
                        )
                    }
                }
            }
            HorizontalDivider(
                modifier = Modifier
                    .height(1.dp)
                    .padding(vertical = 4.dp),
                color = Color(color = 0x0DFFFFFF),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemCardPreview() {
    ItemCard(
        item =
            SearchResult(
                id = "aa",
                thumbnail = "https://http2.mlstatic.com/D_805370-MLA43144907870_082020-I.jpg",
                title = "Iphone 14 Pro Max",
                originalPrice = null,
                price = 250.0,
                freeShipping = true,
                currencyId = "BRL",
            ),
    )
}

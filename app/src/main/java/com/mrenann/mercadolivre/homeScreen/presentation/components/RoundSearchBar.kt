package com.mrenann.mercadolivre.homeScreen.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import compose.icons.EvaIcons
import compose.icons.evaicons.Outline
import compose.icons.evaicons.outline.Search

@Composable
fun RoundSearchBar(
    modifier: Modifier = Modifier,
    query: String? = null,
    onClick: () -> Unit
) {
    val displayText = query?.takeIf { it.isNotBlank() } ?: "Buscar no Mercado Livre"
    val displayColor = if (query.isNullOrBlank()) Color.Gray else Color.Black

    Row(
        modifier = modifier
            .clip(CircleShape)
            .background(color = Color.White, shape = CircleShape)
            .clickable { onClick() }
            .padding(vertical = 6.dp, horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            modifier = Modifier.size(18.dp),
            imageVector = EvaIcons.Outline.Search,
            tint = Color.Gray,
            contentDescription = "Search Icon",
        )
        Text(
            text = displayText,
            color = displayColor,
            fontSize = 14.sp
        )
    }
}

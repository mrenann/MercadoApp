package com.mrenann.mercadolivre.detailsScreen.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.lyricist.strings

@Composable
fun DescriptionContent(description: String) {
    val strings = strings.detailsStrings
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 12.dp),
        verticalArrangement = Arrangement.spacedBy(space = 4.dp)
    ) {
        Text(text = strings.description, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text(
            text = description.takeIf { it.isNotBlank() }
                ?: strings.withoutDescription,
            fontSize = 14.sp
        )
    }
}
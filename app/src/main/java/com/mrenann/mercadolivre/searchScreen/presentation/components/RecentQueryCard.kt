package com.mrenann.mercadolivre.searchScreen.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import compose.icons.EvaIcons
import compose.icons.evaicons.Outline
import compose.icons.evaicons.outline.Clock

@Composable
fun RecentQueryCard(string: String, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
            .padding(horizontal = 12.dp, vertical = 12.dp)
        
    ) {
        Icon(
            imageVector = EvaIcons.Outline.Clock,
            contentDescription = "",
        )
        Text(
            text = string,
            modifier = Modifier.padding(horizontal = 12.dp)
        )

    }
}
package com.mrenann.mercadolivre.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mrenann.mercadolivre.ui.theme.YellowAccent
import compose.icons.EvaIcons
import compose.icons.evaicons.Outline
import compose.icons.evaicons.outline.ChevronLeft
import compose.icons.evaicons.outline.ChevronRight
import compose.icons.evaicons.outline.Pin
import compose.icons.evaicons.outline.ShoppingCart

@Composable
fun Header(
    query: String? = null,
    onSearchClick: () -> Unit,
    onBackClick: (() -> Unit)? = null
) {

    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .background(
                    color = YellowAccent,
                )
                .padding(all = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            onBackClick?.let {
                IconButton(onClick = { onBackClick() }) {
                    Icon(
                        imageVector = EvaIcons.Outline.ChevronLeft,
                        contentDescription = "Back",
                    )
                }
            }
            RoundSearchBar(
                query = query,
                modifier = Modifier.weight(1F),
                onClick = { onSearchClick() }
            )
            IconButton(onClick = { /* NONE */ }) {
                Icon(
                    imageVector = EvaIcons.Outline.ShoppingCart,
                    contentDescription = "Back",
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement =
                Arrangement.spacedBy(
                    space = 2.dp,
                    alignment = Alignment.CenterHorizontally,
                ),
        ) {
            Icon(
                modifier = Modifier.size(16.dp),
                imageVector = EvaIcons.Outline.Pin,
                contentDescription = "Back",
            )
            Text("Desafio Mobile")
            Icon(
                imageVector = EvaIcons.Outline.ChevronRight,
                contentDescription = "Back",
            )
        }
    }
}

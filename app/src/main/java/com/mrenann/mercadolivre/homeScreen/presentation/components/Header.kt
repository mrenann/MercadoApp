package com.mrenann.mercadolivre.homeScreen.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mrenann.mercadolivre.ui.theme.YellowAccent
import compose.icons.EvaIcons
import compose.icons.evaicons.Outline
import compose.icons.evaicons.outline.ChevronRight
import compose.icons.evaicons.outline.Pin
import compose.icons.evaicons.outline.ShoppingCart

@Composable
fun Header(onSearchClick: () -> Unit) {
    var searchQuery by remember { mutableStateOf("") }

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
            horizontalArrangement =
                Arrangement.spacedBy(
                    space = 12.dp,
                    alignment = Alignment.CenterHorizontally,
                ),
        ) {
            SearchBar(
                modifier =
                    Modifier
                        .weight(1F)
                        .clickable {
                            onSearchClick()
                        },
                query = searchQuery,
                onQueryChange = { searchQuery = it },
                onSearch = {},
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

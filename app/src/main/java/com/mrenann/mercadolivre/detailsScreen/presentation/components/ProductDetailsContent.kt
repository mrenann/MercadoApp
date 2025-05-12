package com.mrenann.mercadolivre.detailsScreen.presentation.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.mrenann.mercadolivre.core.data.remote.model.details.Picture
import com.mrenann.mercadolivre.detailsScreen.presentation.screenModel.DetailsScreenModel

@Composable
fun ProductDetailsContent(
    state: DetailsScreenModel.State.Result,
    onImageClick: (List<Picture>, Int) -> Unit
) {
    LazyColumn {
        item {
            ItemDetailsContent(state.product.item, onImageClick)
        }
        item {
            DescriptionContent(description = state.product.description)
        }
    }
}
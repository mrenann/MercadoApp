package com.mrenann.mercadolivre.detailsScreen.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mrenann.mercadolivre.core.utils.skeletonColor

@Composable
fun TopicBoxShimmer() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(space = 12.dp)
    ) {
        Box(
            modifier = Modifier
                .size(20.dp)
                .background(
                    brush = skeletonColor,
                    shape = CircleShape
                )

        )
        BoxShimmer(
            fraction = .5F,
            height = 14.dp
        )
    }
}
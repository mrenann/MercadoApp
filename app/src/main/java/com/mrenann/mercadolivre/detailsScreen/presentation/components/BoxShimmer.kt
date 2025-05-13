package com.mrenann.mercadolivre.detailsScreen.presentation.components

import androidx.annotation.FloatRange
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.mrenann.mercadolivre.core.utils.skeletonColor

@Composable
fun BoxShimmer(
    @FloatRange fraction: Float = 1f,
    height: Dp
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(fraction = fraction)
            .height(height)
            .background(
                brush = skeletonColor,
            )

    )
}
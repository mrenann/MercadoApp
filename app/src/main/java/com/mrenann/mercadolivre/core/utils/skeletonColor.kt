package com.mrenann.mercadolivre.core.utils

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import com.mrenann.mercadolivre.core.utils.ColorConstraints.AnimationDurationDs
import com.mrenann.mercadolivre.core.utils.ColorConstraints.Multiplier

private object ColorConstraints {
    const val Multiplier = 3
    const val AnimationDurationDs = 375
}

val skeletonColor: Brush
    @Composable
    get() {
        val infiniteTransition =
            rememberInfiniteTransition(label = "infiniteTransition")
        val localConfig = LocalConfiguration.current
        val target = (localConfig.screenWidthDp * Multiplier).toFloat()
        val scale by
        infiniteTransition.animateFloat(
            initialValue = 0F,
            targetValue = target,
            animationSpec =
                infiniteRepeatable(
                    animation = tween(AnimationDurationDs, easing = LinearEasing),
                    repeatMode = RepeatMode.Restart
                ), label = "shimmer"
        )

        val skeletonColor = Brush.linearGradient(
            colors = listOf(
                Color.Gray.copy(alpha = 0.5F),
                Color.Gray.copy(alpha = 0.1F),
                Color.Gray.copy(alpha = 0.5F),
            ),
            end = Offset(x = scale, y = scale)
        )
        return skeletonColor
    }
package com.mrenann.mercadolivre.homeScreen.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(color = 0xFFF0F0F0))
            .testTag("home_content_container")
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .testTag("home_content_column")
        ) {
            BannerSection(modifier = Modifier.testTag("banner_section"))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeContentPreview() {
    Surface {
        HomeContent()
    }
}
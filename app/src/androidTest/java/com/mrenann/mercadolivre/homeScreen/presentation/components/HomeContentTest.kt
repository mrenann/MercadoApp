package com.mrenann.mercadolivre.homeScreen.presentation.components

import androidx.compose.ui.test.filterToOne
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeContentIntegrationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun homeContent_renders_correctly() {
        composeTestRule.setContent {
            HomeContent()
        }

        composeTestRule.onNodeWithTag("home_content_container").assertExists()
        composeTestRule.onNodeWithTag("banner_section").assertExists()
        composeTestRule.onNodeWithTag("home_content_container")
            .onChildren()
            .filterToOne(hasTestTag("home_content_column"))
            .assertExists()
    }

}

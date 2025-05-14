package com.mrenann.mercadolivre.core.presentation.components

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RoundSearchBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun roundSearchBar_without_query_displays_the_bar() {
        composeTestRule.setContent {
            RoundSearchBar(
                modifier = Modifier.testTag("search_bar"),
                query = null,
                onClick = { }
            )
        }

        composeTestRule.onNodeWithTag("search_bar")
            .assertIsDisplayed()
    }

    @Test
    fun roundSearchBar_with_query_displays_text() {
        val testQuery = "camisa"

        composeTestRule.setContent {
            RoundSearchBar(
                modifier = Modifier.testTag("search_bar"),
                query = testQuery,
                onClick = {}
            )
        }

        composeTestRule.onNodeWithTag("search_bar")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText(testQuery)
            .assertIsDisplayed()
    }

    @Test
    fun roundSearchBar_when_clicked_invokes_onClick_callback() {
        var wasClicked = false

        composeTestRule.setContent {
            RoundSearchBar(
                modifier = Modifier.testTag("search_bar"),
                query = null,
                onClick = { wasClicked = true }
            )
        }

        composeTestRule.onNodeWithTag("search_bar").performClick()
        assert(wasClicked) { "onClick não aconteceu" }
    }

}

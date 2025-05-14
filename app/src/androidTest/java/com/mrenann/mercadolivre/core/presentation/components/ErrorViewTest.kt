package com.mrenann.mercadolivre.core.presentation.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ErrorViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun errorView_displays_correctly() {
        val testMessage = "Não foi possível conectar ao servidor"
        val testButtonText = "Tentar novamente"

        composeTestRule.setContent {
            MaterialTheme {
                ErrorView(
                    message = testMessage,
                    buttonText = testButtonText,
                    onButtonClick = { }
                )
            }
        }

        composeTestRule.onNodeWithTag("error_view_container")
            .assertIsDisplayed()
        composeTestRule.onNodeWithTag("error_view_content")
            .assertIsDisplayed()
        composeTestRule.onNodeWithTag("error_title")
            .assertIsDisplayed()
            .assertTextEquals("Um erro ocorreu!")
        composeTestRule.onNodeWithTag("error_message")
            .assertIsDisplayed()
            .assertTextEquals(testMessage)
        composeTestRule.onNodeWithTag("error_icon")
            .assertIsDisplayed()
    }

    @Test
    fun errorView_when_clicked_invokes() {
        var buttonClicked = false
        val testMessage = "Não foi possível conectar ao servidor"
        val testButtonText = "Tentar novamente"
        composeTestRule.setContent {
            ErrorView(
                message = testMessage,
                buttonText = testButtonText,
                onButtonClick = { buttonClicked = true }
            )
        }

        composeTestRule.onNodeWithTag("error_retry_button").performClick()
        assert(buttonClicked) { "Botão não foi clicado" }
    }

    @Test
    fun errorView_withCustomColor_usesSpecifiedColor() {
        val customColor = Color.Blue
        val testMessage = "Não foi possível conectar ao servidor"
        val testButtonText = "Tentar novamente"
        composeTestRule.setContent {
            ErrorView(
                message = testMessage,
                buttonText = testButtonText,
                onButtonClick = { },
                primaryButtonColor = customColor
            )
        }

        composeTestRule.onNodeWithTag("error_retry_button")
            .assertIsDisplayed()
    }

    @Test
    fun errorView_multipleButtonClicks_invokeCallbackMultipleTimes() {
        var clickCount = 0
        val testMessage = "Não foi possível conectar ao servidor"
        val testButtonText = "Tentar novamente"
        composeTestRule.setContent {
            ErrorView(
                message = testMessage,
                buttonText = testButtonText,
                onButtonClick = { clickCount++ }
            )
        }

        composeTestRule.onNodeWithTag("error_retry_button").performClick()
        composeTestRule.onNodeWithTag("error_retry_button").performClick()
        composeTestRule.onNodeWithTag("error_retry_button").performClick()

        assert(clickCount == 3) { "Devia ser chamado 3 vezes mas foi $clickCount" }
    }
}
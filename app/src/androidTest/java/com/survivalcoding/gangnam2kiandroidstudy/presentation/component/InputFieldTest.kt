package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextReplacement
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class InputFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testInputField() {
        var actual = ""

        composeTestRule.setContent {
            InputField(
                label = "Label",
                value = "Value",
                onValueChange = {
                    actual = it
                },
                placeholder = "Placeholder",
            )
        }

        composeTestRule.onNodeWithText("Label").assertIsDisplayed()
        composeTestRule.onNodeWithText("Value").assertIsDisplayed()
        composeTestRule.onNodeWithText("Placeholder").assertIsNotDisplayed()

        val expected = "Change Value"
        composeTestRule.onNodeWithText("Value").performTextReplacement(expected)
        assertEquals(expected, actual)
    }

    @Test
    fun testInputFieldPlaceholder() {
        var actual by mutableStateOf("")

        composeTestRule.setContent {
            InputField(
                label = "Label",
                value = actual,
                onValueChange = {
                    actual = it
                },
                placeholder = "Placeholder",
            )
        }

        composeTestRule.onNodeWithText("Label").assertIsDisplayed()
        composeTestRule.onNodeWithText("Placeholder").assertIsDisplayed()

        val expected = "Input Value"
        composeTestRule.onNodeWithText("Placeholder").performTextReplacement(expected)
        composeTestRule.onNodeWithText("Placeholder").assertIsNotDisplayed()
        assertEquals(expected, actual)
    }
}
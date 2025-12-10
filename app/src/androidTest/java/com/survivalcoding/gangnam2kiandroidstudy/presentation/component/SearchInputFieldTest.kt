package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertIsNotFocused
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTextReplacement
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class SearchInputFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testSearchInputField() {
        var actual = ""

        composeTestRule.setContent {
            SearchInputField(
                value = "Value",
                onValueChange = {
                    actual = it
                },
                placeholder = "Placeholder",
            )
        }

        composeTestRule.onNodeWithText("Value").assertIsDisplayed()
        composeTestRule.onNodeWithText("Placeholder").assertIsNotDisplayed()

        val expected = "Change Value"
        composeTestRule.onNodeWithText("Value").performTextReplacement(expected)
        assertEquals(expected, actual)
    }

    @Test
    fun testSearchInputFieldPlaceholder() {
        var actual by mutableStateOf("")

        composeTestRule.setContent {
            SearchInputField(
                value = actual,
                onValueChange = {
                    actual = it
                },
                placeholder = "Placeholder",
            )
        }

        composeTestRule.onNodeWithText("Placeholder").assertIsDisplayed()
        composeTestRule.onNode(hasSetTextAction()).assertIsNotFocused()

        val expected = "Input Value"
        composeTestRule.onNode(hasSetTextAction()).performTextInput(expected)
        composeTestRule.onNodeWithText(expected).assertIsDisplayed()
        composeTestRule.onNodeWithText("Placeholder").assertIsNotDisplayed()
        assertEquals(expected, actual)
    }
}
package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class BigButtonTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testBigButton() {
        var counter = 0

        composeTestRule.setContent {
            BigButton(
                text = "Button",
                onClick = {
                    counter++
                },
            )
        }

        composeTestRule.onNodeWithText("Button").assertIsDisplayed()
        composeTestRule.onNodeWithText("Button").performClick()

        assertEquals(1, counter)
    }
}
package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class RatingDialogButtonTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testRatingDialogButton() {
        var counter = 0

        composeTestRule.setContent {
            RatingDialogButton(
                text = "Send",
                onClick = {
                    counter++
                },
            )
        }

        composeTestRule.onNodeWithText("Send").assertIsDisplayed()
        composeTestRule.onNodeWithText("Send").performClick()
        assertEquals(1, counter)
    }
}
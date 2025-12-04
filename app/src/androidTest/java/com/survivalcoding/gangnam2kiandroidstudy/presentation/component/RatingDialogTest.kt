package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Rule
import org.junit.Test

class RatingDialogTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testRatingDialog() {
        var showDialog by mutableStateOf(true)
        var score = 0

        composeTestRule.setContent {
            if (showDialog) {
                RatingDialog(
                    title = "Rate recipe",
                    actionName = "Send",
                    onDismissRequest = { showDialog = false },
                ) {
                    showDialog = false
                    score = it
                }
            }
        }

        composeTestRule.onNodeWithText("Rate recipe").assertIsDisplayed()
        composeTestRule.onNodeWithText("Send").assertIsDisplayed()

        composeTestRule.onNodeWithContentDescription("star 2").performClick()
        composeTestRule.onNodeWithText("Send").performClick()
        assertEquals(3, score)
        assertFalse(showDialog)

        composeTestRule.onNodeWithText("Rate recipe").assertIsNotDisplayed()
        composeTestRule.onNodeWithText("Send").assertIsNotDisplayed()
    }
}
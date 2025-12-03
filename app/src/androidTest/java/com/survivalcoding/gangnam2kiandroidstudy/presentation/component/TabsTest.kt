package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class TabsTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testTabs() {
        var index by mutableStateOf(0)

        composeTestRule.setContent {
            Tabs(
                labels = listOf("First", "Second"),
                selectedIndex = index,
                onValueChange = {
                    index = it
                },
            )
        }

        composeTestRule.onNodeWithText("First").assertIsDisplayed()
        composeTestRule.onNodeWithText("Second").assertIsDisplayed()

        composeTestRule.onNodeWithText("Second").performClick()
        assertEquals(1, index)
    }
}
package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.survivalcoding.gangnam2kiandroidstudy.data.model.CategoryFilterType
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class RecipeCategorySelectorTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testRecipeCategorySelector() {
        var selectedCategory = CategoryFilterType.ALL

        composeTestRule.setContent {
            RecipeCategorySelector(
                category = selectedCategory,
                onCategorySelect = { selectedCategory = it },
            )
        }

        composeTestRule.onNodeWithText(selectedCategory.label).assertIsDisplayed()
        composeTestRule.onNodeWithText(selectedCategory.label).assertHasClickAction()

        val changeCategory = CategoryFilterType.CEREAL
        composeTestRule.onNodeWithText(changeCategory.label).performClick()
        assertEquals(changeCategory, selectedCategory)
    }
}
package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import org.junit.Rule
import org.junit.Test

class DishCardTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testDishCard() {
        val recipe = Recipe(
            id = 1,
            name = "Vegetable and Chicken",
            imageUrl = "",
            chef = "Laura Goodman",
            time = 20,
            rating = 4.5,
        )

        composeTestRule.setContent {
            DishCard(
                recipe = recipe,
            )
        }

        composeTestRule.onNodeWithContentDescription("recipe image").assertIsDisplayed()
        composeTestRule.onNodeWithText("${recipe.rating}").assertIsDisplayed()
        composeTestRule.onNodeWithText(recipe.name).assertIsDisplayed()
        composeTestRule.onNodeWithText("Time").assertIsDisplayed()
        composeTestRule.onNodeWithText("${recipe.time} Mins").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("unsaved bookmark").assertIsDisplayed()
    }
}
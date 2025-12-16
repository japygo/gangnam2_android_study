package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import org.junit.Rule
import org.junit.Test

class RecipeCardTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testRecipeCard() {
        val recipe = Recipe(
            id = 1,
            name = "spice roasted chicken with flavored rice",
            imageUrl = "https://cdn.pixabay.com/photo/2017/11/10/15/04/steak-2936531_1280.jpg",
            chef = "Chef John",
            time = 20,
            rating = 4.0,
        )

        composeTestRule.setContent {
            RecipeCard(recipe = recipe)
        }

        composeTestRule.onNodeWithText("spice roasted chicken with flavored rice")
            .assertIsDisplayed()
        composeTestRule.onNodeWithText("By Chef John").assertIsDisplayed()
        composeTestRule.onNodeWithText("20 min").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("recipe image").assertIsDisplayed()
    }
}
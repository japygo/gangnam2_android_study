package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.savedrecipes

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.MockRecipeDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepositoryImpl
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class SavedRecipesScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testSavedRecipesScreen() {
        val viewModel = SavedRecipesViewModel(
            repository = RecipeRepositoryImpl(
                dataSource = MockRecipeDataSourceImpl(),
            ),
        )

        composeTestRule.setContent {
            SavedRecipesScreen(
                viewModel = viewModel,
            )
        }

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText("Saved Recipes").assertIsDisplayed()

        val recipes = viewModel.recipes.value

        assertEquals(10, recipes.size)

        recipes.let { data ->
            (0..3).forEach { index ->
                composeTestRule.onNodeWithText(data[index].name).assertIsDisplayed()
                composeTestRule.onNodeWithText("By ${data[index].chef}").assertIsDisplayed()
            }
        }
    }
}
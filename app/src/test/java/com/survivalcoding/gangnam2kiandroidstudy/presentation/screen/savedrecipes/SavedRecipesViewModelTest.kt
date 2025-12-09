package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.savedrecipes

import com.survivalcoding.gangnam2kiandroidstudy.core.NetworkError
import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.test.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SavedRecipesViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun testSavedRecipesViewModel() = runTest {
        val viewModel = SavedRecipesViewModel(
            object : RecipeRepository {
                override suspend fun getSavedRecipes(): Result<List<Recipe>, NetworkError> {
                    return Result.Success(
                        listOf(
                            Recipe(
                                name = "Test Recipe",
                                imageUrl = "imageUrl",
                                chef = "chef",
                                time = 10,
                                rating = 4.5,
                            ),
                            Recipe(
                                name = "Test Recipe2",
                                imageUrl = "imageUrl",
                                chef = "chef",
                                time = 10,
                                rating = 4.5,
                            ),
                        ),
                    )
                }
            },
        )

        advanceUntilIdle()

        val recipes = viewModel.recipes.value

        assertEquals(2, recipes.size)
        assertEquals("Test Recipe", recipes[0].name)
        assertEquals("Test Recipe2", recipes[1].name)
    }
}
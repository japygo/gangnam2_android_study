@file:OptIn(ExperimentalCoroutinesApi::class)

package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.savedrecipes

import com.survivalcoding.gangnam2kiandroidstudy.core.AppResult
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.test.MainDispatcherRule
import io.mockk.MockKAnnotations
import io.mockk.bdd.coGiven
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SavedRecipesViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @MockK
    private lateinit var repository: RecipeRepository

    private lateinit var viewModel: SavedRecipesViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun testSavedRecipesViewModel() = runTest {
        coGiven { repository.getSavedRecipes() } returns AppResult.Success(
            listOf(
                Recipe(
                    id = 1,
                    name = "Test Recipe",
                    imageUrl = "imageUrl",
                    chef = "chef",
                    time = 10,
                    rating = 4.5,
                ),
                Recipe(
                    id = 2,
                    name = "Test Recipe2",
                    imageUrl = "imageUrl",
                    chef = "chef",
                    time = 10,
                    rating = 4.5,
                ),
            ),
        )

        viewModel = SavedRecipesViewModel(repository)

        advanceUntilIdle()

        val recipes = viewModel.recipes.value

        assertEquals(2, recipes.size)
        assertEquals("Test Recipe", recipes[0].name)
        assertEquals("Test Recipe2", recipes[1].name)
    }
}
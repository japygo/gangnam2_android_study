package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.searchrecipes

import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.test.MainDispatcherRule
import io.mockk.MockKAnnotations
import io.mockk.bdd.coGiven
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@FlowPreview
@ExperimentalCoroutinesApi
class SearchRecipesViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @MockK
    private lateinit var repository: RecipeRepository

    private lateinit var viewModel: SearchRecipesViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun fetchRecipes() = runTest {
        coGiven { repository.getRecipes(any()) } returns Result.Success(
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

        viewModel = SearchRecipesViewModel(repository)

        advanceUntilIdle()

        viewModel.fetchRecipes("test")

        val recipes = viewModel.uiState.value.recipes

        assertEquals(2, recipes.size)
    }

    @Test
    fun onSearchTextChange() {
        viewModel = SearchRecipesViewModel(repository)

        assertTrue(viewModel.uiState.value.searchText.isEmpty())

        val searchText = "test"
        viewModel.onSearchTextChange(searchText)

        assertEquals(searchText, viewModel.uiState.value.searchText)
    }

    @Test
    fun setLoading() {
        viewModel = SearchRecipesViewModel(repository)

        assertFalse(viewModel.uiState.value.isLoading)

        viewModel.setLoading(true)

        assertTrue(viewModel.uiState.value.isLoading)
    }
}
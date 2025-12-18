@file:OptIn(ExperimentalCoroutinesApi::class)

package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.searchrecipes

import com.survivalcoding.gangnam2kiandroidstudy.core.AppResult
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.CategoryFilterType
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RateFilterType
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeSearchFilter
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.TimeFilterType
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.test.MainDispatcherRule
import io.mockk.MockKAnnotations
import io.mockk.bdd.coGiven
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

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
        coGiven { repository.getRecipes(any()) } returns AppResult.Success(
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

        viewModel = SearchRecipesViewModel(repository)

        advanceUntilIdle()

        val recipes = viewModel.uiState.value.recipes

        assertEquals(2, recipes.size)
    }

    @Test
    fun changeSearchText() {
        viewModel = SearchRecipesViewModel(repository)

        assertTrue(viewModel.uiState.value.searchText.isEmpty())

        val searchText = "test"
        viewModel.onAction(SearchRecipeAction.ChangeQuery(searchText))

        assertEquals(searchText, viewModel.uiState.value.searchText)
    }

    @Test
    fun showBottomSheet() {
        viewModel = SearchRecipesViewModel(repository)

        assertFalse(viewModel.uiState.value.isSheetVisible)

        viewModel.onAction(SearchRecipeAction.OnFilterClick)

        assertTrue(viewModel.uiState.value.isSheetVisible)
    }

    @Test
    fun hideBottomSheet() {
        viewModel = SearchRecipesViewModel(repository)

        viewModel.onAction(SearchRecipeAction.OnFilterClick)

        assertTrue(viewModel.uiState.value.isSheetVisible)

        viewModel.onAction(FilterAction.OnDismissRequest)

        assertFalse(viewModel.uiState.value.isSheetVisible)
    }

    @Test
    fun changeSearchFilter() {
        viewModel = SearchRecipesViewModel(repository)

        val time = TimeFilterType.NEWEST
        val rate = RateFilterType.FIVE
        val category = CategoryFilterType.CEREAL
        val searchFilter = RecipeSearchFilter(time, rate, category)

        viewModel.onAction(FilterAction.ChangeFilter(searchFilter))

        assertEquals(time, viewModel.uiState.value.searchFilter.time)
        assertEquals(rate, viewModel.uiState.value.searchFilter.rate)
        assertEquals(category, viewModel.uiState.value.searchFilter.category)
    }
}
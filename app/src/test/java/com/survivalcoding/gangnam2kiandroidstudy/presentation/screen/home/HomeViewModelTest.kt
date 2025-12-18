@file:OptIn(ExperimentalCoroutinesApi::class)

package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import com.survivalcoding.gangnam2kiandroidstudy.core.AppResult
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.CategoryFilterType
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetNewRecipesUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetRecipesUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.ToggleBookmarkUseCase
import com.survivalcoding.gangnam2kiandroidstudy.test.MainDispatcherRule
import io.mockk.MockKAnnotations
import io.mockk.bdd.coGiven
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @MockK
    private lateinit var getRecipesUseCase: GetRecipesUseCase

    @MockK
    private lateinit var getNewRecipesUseCase: GetNewRecipesUseCase

    @MockK
    private lateinit var toggleBookmarkUseCase: ToggleBookmarkUseCase

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun fetchRecipes() = runTest {
        coGiven { getRecipesUseCase(any()) } returns
                AppResult.Success(
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
        coGiven { getNewRecipesUseCase() } returns AppResult.Success(emptyList())

        viewModel = HomeViewModel(
            getRecipesUseCase,
            getNewRecipesUseCase,
            toggleBookmarkUseCase,
        )

        assertTrue(viewModel.uiState.value.isLoading)

        advanceUntilIdle()

        val recipes = viewModel.uiState.value.recipes

        assertEquals(2, recipes.size)
        assertFalse(viewModel.uiState.value.isLoading)
    }

    @Test
    fun fetchNewRecipes() = runTest {
        coGiven { getRecipesUseCase(any()) } returns AppResult.Success(emptyList())
        coGiven { getNewRecipesUseCase() } returns
                AppResult.Success(
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

        viewModel = HomeViewModel(
            getRecipesUseCase,
            getNewRecipesUseCase,
            toggleBookmarkUseCase,
        )

        assertTrue(viewModel.uiState.value.isNewRecipesLoading)

        advanceUntilIdle()

        val recipes = viewModel.uiState.value.newRecipes

        assertEquals(2, recipes.size)
        assertFalse(viewModel.uiState.value.isNewRecipesLoading)
    }

    @Test
    fun changeCategory() {
        coGiven { getRecipesUseCase(any()) } returns AppResult.Success(emptyList())
        coGiven { getNewRecipesUseCase() } returns AppResult.Success(emptyList())

        viewModel = HomeViewModel(
            getRecipesUseCase,
            getNewRecipesUseCase,
            toggleBookmarkUseCase,
        )

        val category = CategoryFilterType.CEREAL

        viewModel.onAction(HomeAction.SelectCategory(category))

        assertEquals(category, viewModel.uiState.value.category)
    }

    @Test
    fun changeQuery() {
        coGiven { getRecipesUseCase(any()) } returns AppResult.Success(emptyList())
        coGiven { getNewRecipesUseCase() } returns AppResult.Success(emptyList())

        viewModel = HomeViewModel(
            getRecipesUseCase,
            getNewRecipesUseCase,
            toggleBookmarkUseCase,
        )

        val query = "test"

        viewModel.onAction(HomeAction.ChangeQuery(query))

        assertEquals(query, viewModel.uiState.value.query)
    }

    @Test
    fun debounceQuery() = runTest {
        coGiven { getRecipesUseCase(any()) } returns AppResult.Success(emptyList())
        coGiven { getNewRecipesUseCase() } returns AppResult.Success(emptyList())

        viewModel = HomeViewModel(
            getRecipesUseCase,
            getNewRecipesUseCase,
            toggleBookmarkUseCase,
        )
        coVerify(exactly = 0) { getRecipesUseCase(any()) }

        viewModel.onAction(HomeAction.ChangeQuery("test1"))
        advanceTimeBy(HomeViewModel.DEBOUNCE_TIMEOUT_MILLIS - 100L)

        coVerify(exactly = 1) { getRecipesUseCase(any()) }
        assertEquals("test1", viewModel.uiState.value.query)

        viewModel.onAction(HomeAction.ChangeQuery("test2"))
        advanceTimeBy(HomeViewModel.DEBOUNCE_TIMEOUT_MILLIS + 100L)

        coVerify(exactly = 2) { getRecipesUseCase(any()) }
        assertEquals("test2", viewModel.uiState.value.query)
    }
}
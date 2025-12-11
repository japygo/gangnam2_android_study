package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import com.survivalcoding.gangnam2kiandroidstudy.core.AppResult
import com.survivalcoding.gangnam2kiandroidstudy.data.model.CategoryFilterType
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.test.MainDispatcherRule
import io.mockk.MockKAnnotations
import io.mockk.bdd.coGiven
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@FlowPreview
@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @MockK
    private lateinit var repository: RecipeRepository

    @InjectMockKs
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun fetchRecipes() = runTest {
        coGiven { repository.getRecipes(any()) } returns
                AppResult.Success(
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

        viewModel.fetchRecipes()

        advanceUntilIdle()

        val recipes = viewModel.uiState.value.recipes

        assertEquals(2, recipes.size)
    }

    @Test
    fun changeCategory() {
        val category = CategoryFilterType.CEREAL

        viewModel.changeCategory(category)

        assertEquals(category, viewModel.uiState.value.category)
    }

    @Test
    fun changeQuery() {
        val query = "test"

        viewModel.changeQuery(query)

        assertEquals(query, viewModel.uiState.value.query)
    }

    @Test
    fun debounceQuery() = runTest {
        coGiven { repository.getRecipes(any()) } returns AppResult.Success(emptyList())

        viewModel.changeQuery("test1")
        advanceTimeBy(HomeViewModel.DEBOUNCE_TIMEOUT_MILLIS - 100L)

        coVerify(exactly = 0) { repository.getRecipes(any()) }
        assertEquals("test1", viewModel.uiState.value.query)

        viewModel.changeQuery("test2")
        advanceTimeBy(HomeViewModel.DEBOUNCE_TIMEOUT_MILLIS + 100L)

        coVerify(exactly = 1) { repository.getRecipes(any()) }
        assertEquals("test2", viewModel.uiState.value.query)
    }
}
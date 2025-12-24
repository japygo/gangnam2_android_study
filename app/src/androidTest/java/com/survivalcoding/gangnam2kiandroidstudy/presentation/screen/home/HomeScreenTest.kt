package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.MockBookmarkRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.MockErrorRecipeRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.MockRecipeRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Profile
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetNewRecipesUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetRecipesUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.ToggleBookmarkUseCase
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testHomeScreen_FailureScenario() {
        val viewModel = HomeViewModel(
            getRecipesUseCase = GetRecipesUseCase(MockErrorRecipeRepositoryImpl),
            getNewRecipesUseCase = GetNewRecipesUseCase(MockErrorRecipeRepositoryImpl),
            toggleBookmarkUseCase = ToggleBookmarkUseCase(MockBookmarkRepositoryImpl),
        )

        composeTestRule.setContent {
            val uiState by viewModel.uiState.collectAsState()
            HomeScreen(
                uiState = uiState,
                onAction = viewModel::onAction,
            )
        }

        // 실패 시 "데이터가 없습니다." 메시지가 표시되는지 확인
        composeTestRule.onAllNodesWithText("데이터가 없습니다.").onFirst().assertIsDisplayed()
        composeTestRule.onNode(hasText("Traditional", substring = true)).assertDoesNotExist()
    }

    @Test
    fun testBookmarkToggle() {
        composeTestRule.setContent {
            val recipes = remember {
                mutableStateOf(
                    listOf(
                        MockRecipeRepositoryImpl.mockRecipes.first().copy(isSaved = false),
                    ),
                )
            }
            HomeScreen(
                uiState = HomeUiState(
                    recipes = recipes.value,
                ),
                onAction = { action ->
                    if (action is HomeAction.ToggleBookmark) {
                        recipes.value = recipes.value.map {
                            if (it.id == action.recipeId) it.copy(isSaved = !it.isSaved) else it
                        }
                    }
                },
            )
        }

        // Initial state: Not saved
        composeTestRule.onNodeWithContentDescription("unsaved bookmark").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("saved bookmark").assertDoesNotExist()

        // Click bookmark
        composeTestRule.onNodeWithContentDescription("unsaved bookmark").performClick()

        // Verify state changed: Saved
        composeTestRule.onNodeWithContentDescription("saved bookmark").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("unsaved bookmark").assertDoesNotExist()

        // Click again
        composeTestRule.onNodeWithContentDescription("saved bookmark").performClick()

        // Verify state changed back: Not saved
        composeTestRule.onNodeWithContentDescription("unsaved bookmark").assertIsDisplayed()
    }

    @Test
    fun testHomeScreen() {
        composeTestRule.setContent {
            HomeScreen(
                uiState = HomeUiState(
                    recipes = MockRecipeRepositoryImpl.mockRecipes,
                    profile = Profile(
                        id = 1,
                        name = "Jane",
                        imageUrl = "",
                    ),
                ),
            )
        }

        composeTestRule.onNodeWithText("Hello Jane").assertIsDisplayed()
        composeTestRule.onNodeWithText("What are you cooking today?").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("profile image").assertIsDisplayed()

        composeTestRule.onNodeWithText("All").assertIsDisplayed()
        composeTestRule.onNodeWithText("Cereal").assertIsDisplayed()
        composeTestRule.onNodeWithText("Vegetables").assertIsDisplayed()

        composeTestRule.onNode(hasText("Traditional", substring = true)).assertIsDisplayed()
        composeTestRule.onAllNodesWithText("Time").onFirst().assertIsDisplayed()
        composeTestRule.onAllNodesWithText("20 Mins").onFirst().assertIsDisplayed()
    }

    @Test
    fun testNoProfileHomeScreen() {
        composeTestRule.setContent {
            HomeScreen(
                uiState = HomeUiState(
                    profile = null,
                ),
            )
        }

        composeTestRule.onNodeWithText("Hello ").assertIsDisplayed()
        composeTestRule.onNodeWithText("What are you cooking today?").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("profile image").assertIsNotDisplayed()

        composeTestRule.onNodeWithText("All").assertIsDisplayed()
        composeTestRule.onNodeWithText("Cereal").assertIsDisplayed()
        composeTestRule.onNodeWithText("Vegetables").assertIsDisplayed()

        composeTestRule.onNode(hasText("Traditional", substring = true)).assertIsNotDisplayed()
        composeTestRule.onAllNodesWithText("Time").onFirst().assertIsNotDisplayed()
        composeTestRule.onAllNodesWithText("20 Mins").onFirst().assertIsNotDisplayed()
    }

    @Test
    fun testLoadingHomeScreen() {
        composeTestRule.setContent {
            HomeScreen(
                uiState = HomeUiState(
                    isLoading = true,
                ),
            )
        }

        composeTestRule.onNodeWithText("Hello ").assertIsDisplayed()
        composeTestRule.onNodeWithText("What are you cooking today?").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("profile image").assertIsNotDisplayed()

        composeTestRule.onNodeWithText("All").assertIsDisplayed()
        composeTestRule.onNodeWithText("Cereal").assertIsDisplayed()
        composeTestRule.onNodeWithText("Vegetables").assertIsDisplayed()

        composeTestRule.onNodeWithText("Loading...").assertIsDisplayed()
        composeTestRule.onNode(hasText("Traditional", substring = true)).assertIsNotDisplayed()
        composeTestRule.onAllNodesWithText("Time").onFirst().assertIsNotDisplayed()
        composeTestRule.onAllNodesWithText("20 Mins").onFirst().assertIsNotDisplayed()
    }

    @Test
    fun testNoDataHomeScreen() {
        composeTestRule.setContent {
            HomeScreen(
                uiState = HomeUiState(
                    recipes = emptyList(),
                ),
            )
        }

        composeTestRule.onNodeWithText("Hello ").assertIsDisplayed()
        composeTestRule.onNodeWithText("What are you cooking today?").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("profile image").assertIsNotDisplayed()

        composeTestRule.onNodeWithText("All").assertIsDisplayed()
        composeTestRule.onNodeWithText("Cereal").assertIsDisplayed()
        composeTestRule.onNodeWithText("Vegetables").assertIsDisplayed()

        composeTestRule.onAllNodesWithText("데이터가 없습니다.").onFirst().assertIsDisplayed()
        composeTestRule.onNode(hasText("Traditional", substring = true)).assertIsNotDisplayed()
        composeTestRule.onAllNodesWithText("Time").onFirst().assertIsNotDisplayed()
        composeTestRule.onAllNodesWithText("20 Mins").onFirst().assertIsNotDisplayed()
    }
}
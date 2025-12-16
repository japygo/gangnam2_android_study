package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.MockRecipeRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Profile
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

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

        composeTestRule.onNodeWithText("데이터가 없습니다.").assertIsDisplayed()
        composeTestRule.onNode(hasText("Traditional", substring = true)).assertIsNotDisplayed()
        composeTestRule.onAllNodesWithText("Time").onFirst().assertIsNotDisplayed()
        composeTestRule.onAllNodesWithText("20 Mins").onFirst().assertIsNotDisplayed()
    }
}
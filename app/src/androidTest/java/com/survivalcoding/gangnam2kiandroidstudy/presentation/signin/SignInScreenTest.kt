package com.survivalcoding.gangnam2kiandroidstudy.presentation.signin

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test

class SignInScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testSignInScreen() {

        composeTestRule.setContent {
            SignInScreen()
        }

        composeTestRule.onNodeWithText("Hello,").assertIsDisplayed()
        composeTestRule.onNodeWithText("Welcome Back!").assertIsDisplayed()
        composeTestRule.onNodeWithText("Email").assertIsDisplayed()
        composeTestRule.onAllNodesWithText("Enter Password")[0].assertIsDisplayed()
        composeTestRule.onNodeWithText("Forgot Password?").assertIsDisplayed()
        composeTestRule.onNodeWithText("Sign In").assertIsDisplayed()
        composeTestRule.onNodeWithText("Or Sign in With").assertIsDisplayed()
        composeTestRule.onNodeWithText("Don’t have an account?").assertIsDisplayed()
        composeTestRule.onNodeWithText("Sign up").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("google").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("facebook").assertIsDisplayed()

        val testEmail = "test@test.com"
        val testPassword = "1234"

        composeTestRule.onNodeWithText("Enter Email").performTextInput(testEmail)
        composeTestRule.onAllNodesWithText("Enter Password")[1].performTextInput(testPassword)
        composeTestRule.onNodeWithText(testEmail).assertExists()
        composeTestRule.onNodeWithText(testPassword).assertExists()
    }
}
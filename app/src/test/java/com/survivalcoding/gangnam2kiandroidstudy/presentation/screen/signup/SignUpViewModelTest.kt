@file:OptIn(ExperimentalCoroutinesApi::class)

package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.signup

import com.survivalcoding.gangnam2kiandroidstudy.core.AppResult
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.SignUpWithEmailUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.SignUpWithGoogleUseCase
import com.survivalcoding.gangnam2kiandroidstudy.test.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignUpViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val signUpWithEmailUseCase: SignUpWithEmailUseCase = mockk()
    private val signUpWithGoogleUseCase: SignUpWithGoogleUseCase = mockk()

    private lateinit var viewModel: SignUpViewModel

    @Before
    fun setUp() {
        viewModel = SignUpViewModel(signUpWithEmailUseCase, signUpWithGoogleUseCase)
    }

    @Test
    fun signUp() = runTest {
        val email = "test@test.com"
        val pw = "1234"
        coEvery { signUpWithEmailUseCase(email, pw) } returns AppResult.Success(Unit)

        val events = mutableListOf<SignUpEvent>()
        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.event.collect { events.add(it) }
        }

        viewModel.onAction(SignUpAction.OnChangeEmail(email))
        viewModel.onAction(SignUpAction.OnChangePassword(pw))
        viewModel.onAction(SignUpAction.OnChangeConfirmPassword(pw))
        viewModel.onAction(SignUpAction.OnChangeIsChecked(true))
        viewModel.onAction(SignUpAction.OnSignUp)

        advanceUntilIdle()

        assertEquals(SignUpEvent.NavigateToMain, events.first())

        collectJob.cancel()
    }

    @Test
    fun passwordNotMatch() = runTest {
        val events = mutableListOf<SignUpEvent>()
        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.event.collect { events.add(it) }
        }

        viewModel.onAction(SignUpAction.OnChangeEmail("test@test.com"))
        viewModel.onAction(SignUpAction.OnChangePassword("1234"))
        viewModel.onAction(SignUpAction.OnChangeConfirmPassword("5678"))
        viewModel.onAction(SignUpAction.OnChangeIsChecked(true))
        viewModel.onAction(SignUpAction.OnSignUp)

        advanceUntilIdle()

        val lastEvent = events.first() as SignUpEvent.ShowSnackbar
        assertEquals("Passwords don't match", lastEvent.message)

        collectJob.cancel()
    }

    @Test
    fun change() = runTest {
        val name = "test"
        val email = "test@test.com"
        val password = "password"
        val confirmPassword = "password"
        val isChecked = true

        viewModel.onAction(SignUpAction.OnChangeName(name))
        viewModel.onAction(SignUpAction.OnChangeEmail(email))
        viewModel.onAction(SignUpAction.OnChangePassword(password))
        viewModel.onAction(SignUpAction.OnChangeConfirmPassword(confirmPassword))
        viewModel.onAction(SignUpAction.OnChangeIsChecked(isChecked))

        assertEquals(name, viewModel.uiState.value.name)
        assertEquals(email, viewModel.uiState.value.email)
        assertEquals(password, viewModel.uiState.value.password)
        assertEquals(confirmPassword, viewModel.uiState.value.confirmPassword)
        assertEquals(isChecked, viewModel.uiState.value.isChecked)
    }
}
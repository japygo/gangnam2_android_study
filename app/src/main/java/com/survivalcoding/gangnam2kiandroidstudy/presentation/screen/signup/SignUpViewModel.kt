package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.core.AppResult
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.SignUpWithEmailUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.SignUpWithGoogleUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val signUpWithEmailUseCase: SignUpWithEmailUseCase,
    private val signUpWithGoogleUseCase: SignUpWithGoogleUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState = _uiState.asStateFlow()

    private val _event = MutableSharedFlow<SignUpEvent>()
    val event = _event.asSharedFlow()

    fun onAction(action: SignUpAction) {
        when (action) {
            SignUpAction.OnSignUp -> signUp()
            is SignUpAction.OnSignInWithGoogle -> signInWithGoogle(action.idToken)
            SignUpAction.OnSignIn -> navigateToSignIn()
            SignUpAction.RequestGoogleSignIn -> requestGoogleSignIn()
            is SignUpAction.OnChangeName -> changeName(action.name)
            is SignUpAction.OnChangeEmail -> changeEmail(action.email)
            is SignUpAction.OnChangePassword -> changePassword(action.password)
            is SignUpAction.OnChangeConfirmPassword -> changeConfirmPassword(action.confirmPassword)
            is SignUpAction.OnChangeIsChecked -> changeIsChecked(action.isChecked)
        }
    }

    private fun changeName(name: String) {
        _uiState.update { it.copy(name = name) }
    }

    private fun changeEmail(email: String) {
        _uiState.update { it.copy(email = email) }
    }

    private fun changePassword(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    private fun changeConfirmPassword(confirmPassword: String) {
        _uiState.update { it.copy(confirmPassword = confirmPassword) }
    }

    private fun changeIsChecked(isChecked: Boolean) {
        _uiState.update { it.copy(isChecked = isChecked) }
    }

    private fun signUp() {
        val email = uiState.value.email
        val password = uiState.value.password
        val confirmPassword = uiState.value.confirmPassword
        val isChecked = uiState.value.isChecked

        if (email.isBlank()) {
            showSnackbar("Email is empty")
            return
        }

        if (password.isBlank()) {
            showSnackbar("Password is empty")
            return
        }

        if (password != confirmPassword) {
            showSnackbar("Passwords don't match")
            return
        }

        if (!isChecked) {
            showSnackbar("Accept terms & Condition")
            return
        }

        viewModelScope.launch {
            when (val result = signUpWithEmailUseCase(email, password)) {
                is AppResult.Success -> navigateToMain()
                is AppResult.Error -> showSnackbar(result.error)
            }
        }
    }

    private fun signInWithGoogle(idToken: String) {
        viewModelScope.launch {
            when (val result = signUpWithGoogleUseCase(idToken)) {
                is AppResult.Success -> navigateToMain()
                is AppResult.Error -> showSnackbar(result.error)
            }
        }
    }

    private fun requestGoogleSignIn() {
        viewModelScope.launch {
            _event.emit(SignUpEvent.RequestGoogleSignIn)
        }
    }

    private fun navigateToSignIn() {
        viewModelScope.launch {
            _event.emit(SignUpEvent.NavigateToSignIn)
        }
    }

    private fun navigateToMain() {
        viewModelScope.launch {
            _event.emit(SignUpEvent.NavigateToMain)
        }
    }

    private fun showSnackbar(message: String) {
        viewModelScope.launch {
            _event.emit(SignUpEvent.ShowSnackbar(message))
        }
    }
}
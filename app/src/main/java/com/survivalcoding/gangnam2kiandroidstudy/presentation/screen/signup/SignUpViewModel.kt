package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.core.AppResult
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.SignUpWithEmailUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.SignUpWithGoogleUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val signUpWithEmailUseCase: SignUpWithEmailUseCase,
    private val signUpWithGoogleUseCase: SignUpWithGoogleUseCase,
) : ViewModel() {

    private val _event = MutableSharedFlow<SignUpEvent>()
    val event = _event.asSharedFlow()

    fun onAction(action: SignUpAction) {
        when (action) {
            is SignUpAction.OnSignUp -> signUp(action.email, action.password)
            is SignUpAction.OnSignInWithGoogle -> signInWithGoogle(action.idToken)
            SignUpAction.OnSignIn -> navigateToSignIn()
            SignUpAction.RequestGoogleSignIn -> requestGoogleSignIn()
        }
    }

    private fun signUp(email: String, password: String) {
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
package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.signup

sealed interface SignUpEvent {
    data object NavigateToSignIn : SignUpEvent
    data object NavigateToMain : SignUpEvent
    data class ShowSnackbar(val message: String) : SignUpEvent
    data object RequestGoogleSignIn : SignUpEvent
}
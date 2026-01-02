package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.signup

sealed interface SignUpAction {
    data class OnSignUp(val email: String, val password: String) : SignUpAction
    data object OnSignIn : SignUpAction
    data object RequestGoogleSignIn : SignUpAction
    data class OnSignInWithGoogle(val idToken: String) : SignUpAction
}
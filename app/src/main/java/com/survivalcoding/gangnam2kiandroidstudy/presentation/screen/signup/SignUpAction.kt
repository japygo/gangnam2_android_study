package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.signup

sealed interface SignUpAction {
    data object OnSignUp : SignUpAction
    data object OnSignIn : SignUpAction
    data object RequestGoogleSignIn : SignUpAction
    data class OnSignInWithGoogle(val idToken: String) : SignUpAction
    data class OnChangeName(val name: String) : SignUpAction
    data class OnChangeEmail(val email: String) : SignUpAction
    data class OnChangePassword(val password: String) : SignUpAction
    data class OnChangeConfirmPassword(val confirmPassword: String) : SignUpAction
    data class OnChangeIsChecked(val isChecked: Boolean) : SignUpAction
}
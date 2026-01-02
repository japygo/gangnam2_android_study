package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.signup

data class SignUpUiState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isChecked: Boolean = false,
)
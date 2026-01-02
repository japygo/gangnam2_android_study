package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.signup

sealed interface SignUpNavigation {
    data object SignIn : SignUpNavigation
    data object Main : SignUpNavigation
}
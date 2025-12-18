package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

sealed interface HomeNavigation {
    data object OnSearchInputClick : HomeNavigation
    data class OnRecipeClick(val recipeId: Long) : HomeNavigation
}
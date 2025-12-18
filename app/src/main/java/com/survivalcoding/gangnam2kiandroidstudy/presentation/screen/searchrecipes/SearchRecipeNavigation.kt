package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.searchrecipes

sealed interface SearchRecipeNavigation {
    data class OnRecipeClick(val recipeId: Long) : SearchRecipeNavigation
    data object OnBackClick : SearchRecipeNavigation
}
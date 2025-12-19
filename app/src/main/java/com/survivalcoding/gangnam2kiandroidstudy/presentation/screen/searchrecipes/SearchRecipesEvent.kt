package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.searchrecipes

sealed interface SearchRecipesEvent {
    data class ShowSnackbar(val message: String) : SearchRecipesEvent
    data class NavigateToDetails(val recipeId: Long) : SearchRecipesEvent
}
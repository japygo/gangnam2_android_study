package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.savedrecipes

sealed interface SavedRecipesEvent {
    data class NavigateToDetails(val recipeId: Long) : SavedRecipesEvent
}
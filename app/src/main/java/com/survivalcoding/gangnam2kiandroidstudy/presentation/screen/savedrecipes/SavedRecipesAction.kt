package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.savedrecipes

sealed interface SavedRecipesAction {
    data class OnCardClick(val recipeId: Long) : SavedRecipesAction
    data class OnBookmarkClick(val recipeId: Long) : SavedRecipesAction
}
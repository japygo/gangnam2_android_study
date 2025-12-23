package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.savedrecipes

sealed interface SavedRecipesNavigation {
    data class RecipeDetails(val recipeId: Long) : SavedRecipesNavigation
}
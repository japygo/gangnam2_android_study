package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.profile

sealed interface ProfileNavigation {
    data class RecipeDetails(val recipeId: Long) : ProfileNavigation
}
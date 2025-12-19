package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.searchrecipes

sealed interface SearchRecipeAction {
    data class ChangeQuery(val query: String) : SearchRecipeAction
    data object OnFilterClick : SearchRecipeAction
    data class OnCardClick(val recipeId: Long) : SearchRecipeAction
}
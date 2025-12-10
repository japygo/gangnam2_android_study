package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.searchrecipes

import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe

data class SearchRecipesUiState(
    val recipes: List<Recipe> = emptyList(),
    val searchRecipes: List<Recipe> = emptyList(),
    val searchText: String = "",
    val isLoading: Boolean = false,
)
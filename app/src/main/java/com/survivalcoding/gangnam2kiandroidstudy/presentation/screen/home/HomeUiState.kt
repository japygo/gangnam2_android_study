package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import androidx.compose.runtime.Immutable
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.CategoryFilterType
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Profile
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe

@Immutable
data class HomeUiState(
    val category: CategoryFilterType = CategoryFilterType.ALL,
    val recipes: List<Recipe> = emptyList(),
    val isLoading: Boolean = false,
    val query: String = "",
    val profile: Profile? = null,
    val newRecipes: List<Recipe> = emptyList(),
    val isNewRecipesLoading: Boolean = false,
)
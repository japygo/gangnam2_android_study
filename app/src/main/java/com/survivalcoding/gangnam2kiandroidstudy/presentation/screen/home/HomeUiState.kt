package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import com.survivalcoding.gangnam2kiandroidstudy.data.model.CategoryFilterType
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Profile
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe

data class HomeUiState(
    val category: CategoryFilterType = CategoryFilterType.ALL,
    val recipes: List<Recipe> = emptyList(),
    val isLoading: Boolean = false,
    val query: String = "",
    val profile: Profile? = null,
)
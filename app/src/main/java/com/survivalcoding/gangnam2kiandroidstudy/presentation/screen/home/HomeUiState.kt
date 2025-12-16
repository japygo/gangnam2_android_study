package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.CategoryFilterType
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Profile
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe

data class HomeUiState(
    val category: CategoryFilterType = CategoryFilterType.ALL,
    val recipes: List<Recipe> = emptyList(),
    val isLoading: Boolean = false,
    val query: String = "",
    val profile: Profile? = null,
)
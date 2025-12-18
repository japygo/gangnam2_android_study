package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.CategoryFilterType

sealed interface HomeAction {
    data class ChangeQuery(val query: String) : HomeAction
    data class SelectCategory(val category: CategoryFilterType) : HomeAction
    data class ToggleBookmark(val recipeId: Long) : HomeAction
}
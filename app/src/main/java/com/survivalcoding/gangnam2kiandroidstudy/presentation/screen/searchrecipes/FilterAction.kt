package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.searchrecipes

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeSearchFilter

sealed interface FilterAction {
    data object OnDismissRequest : FilterAction
    data class ChangeFilter(val searchFilter: RecipeSearchFilter) : FilterAction
    data object OnFilter : FilterAction
}
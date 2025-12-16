package com.survivalcoding.gangnam2kiandroidstudy.domain.model

data class RecipeSearchFilter(
    val time: TimeFilterType? = null,
    val rate: RateFilterType? = null,
    val category: CategoryFilterType? = null,
) {
    fun isEmpty(): Boolean {
        return time == null && rate == null && category == null
    }

    fun isNotEmpty(): Boolean {
        return !isEmpty()
    }
}
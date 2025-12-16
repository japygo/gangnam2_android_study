package com.survivalcoding.gangnam2kiandroidstudy.domain.model

import com.survivalcoding.gangnam2kiandroidstudy.data.dto.RecipeDto

data class RecipeSearchCondition(
    val searchText: String = "",
    val searchFilter: RecipeSearchFilter = RecipeSearchFilter(),
) {
    fun apply(recipes: List<RecipeDto>): List<RecipeDto> {
        val filtered = recipes.filter { matches(it) }
        return sort(filtered)
    }

    private fun matches(recipe: RecipeDto): Boolean {
        return matchesSearchText(recipe.name) &&
                matchesCategory(recipe.category) &&
                matchesRate(recipe.rating)
    }

    private fun matchesSearchText(searchText: String?): Boolean {
        if (this.searchText.isBlank()) {
            return true
        }
        return searchText?.contains(this.searchText, ignoreCase = true) ?: false
    }

    private fun matchesCategory(category: String?): Boolean {
        return when (searchFilter.category) {
            null -> true
            else -> searchFilter.category.matches(category)
        }
    }

    private fun matchesRate(rate: Double?): Boolean {
        if (searchFilter.rate == null) {
            return true
        }
        if (rate == null) {
            return false
        }
        return when (searchFilter.rate) {
            RateFilterType.FIVE -> rate >= 5.0
            RateFilterType.FOUR -> rate in 4.0..4.9
            RateFilterType.THREE -> rate in 3.0..3.9
            RateFilterType.TWO -> rate in 2.0..2.9
            RateFilterType.ONE -> rate in 0.0..1.9
        }
    }

    private fun sort(recipes: List<RecipeDto>): List<RecipeDto> {
        return when (searchFilter.time) {
            null, TimeFilterType.ALL -> recipes
            TimeFilterType.NEWEST -> recipes.sortedByDescending { it.id }
            TimeFilterType.OLDEST -> recipes.sortedBy { it.id }
            TimeFilterType.POPULARITY -> recipes.sortedByDescending { it.rating }
        }
    }
}
package com.survivalcoding.gangnam2kiandroidstudy.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class RecipesDto(
    val recipes: List<RecipeDto> = emptyList(),
)

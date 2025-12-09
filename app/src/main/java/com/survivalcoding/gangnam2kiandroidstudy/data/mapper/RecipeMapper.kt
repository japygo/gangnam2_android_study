package com.survivalcoding.gangnam2kiandroidstudy.data.mapper

import com.survivalcoding.gangnam2kiandroidstudy.data.dto.RecipeDto
import com.survivalcoding.gangnam2kiandroidstudy.data.dto.RecipesDto
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe

fun RecipesDto.toModel(): List<Recipe> {
    return this.recipes.map(RecipeDto::toModel)
}

fun RecipeDto.toModel(): Recipe {
    return Recipe(
        name = this.name.orEmpty(),
        imageUrl = this.image.orEmpty(),
        chef = this.chef.orEmpty(),
        time = extractTime(this.time ?: "0") ?: 0,
        rating = this.rating ?: 0.0,
    )
}

private fun extractTime(time: String): Int? {
    val regex = Regex("^\\d+")
    return regex.find(time)?.value?.toIntOrNull()
}
package com.survivalcoding.gangnam2kiandroidstudy.data.datasource

import com.survivalcoding.gangnam2kiandroidstudy.core.Response
import com.survivalcoding.gangnam2kiandroidstudy.data.dto.RecipesDto
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeSearchCondition

interface RecipeDataSource {
    suspend fun getSavedRecipes(): Response<RecipesDto>
    suspend fun getRecipes(searchCondition: RecipeSearchCondition): Response<RecipesDto>
}
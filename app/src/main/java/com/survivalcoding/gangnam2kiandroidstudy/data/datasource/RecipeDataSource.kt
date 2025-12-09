package com.survivalcoding.gangnam2kiandroidstudy.data.datasource

import com.survivalcoding.gangnam2kiandroidstudy.core.Response
import com.survivalcoding.gangnam2kiandroidstudy.data.dto.RecipesDto

interface RecipeDataSource {
    suspend fun getSavedRecipes(): Response<RecipesDto>
}
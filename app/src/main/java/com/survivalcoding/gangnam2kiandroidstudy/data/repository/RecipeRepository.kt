package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.core.AppResult
import com.survivalcoding.gangnam2kiandroidstudy.core.NetworkError
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.data.model.RecipeSearchCondition

interface RecipeRepository {
    suspend fun getSavedRecipes(): AppResult<List<Recipe>, NetworkError>
    suspend fun getRecipes(searchCondition: RecipeSearchCondition): AppResult<List<Recipe>, NetworkError>
}
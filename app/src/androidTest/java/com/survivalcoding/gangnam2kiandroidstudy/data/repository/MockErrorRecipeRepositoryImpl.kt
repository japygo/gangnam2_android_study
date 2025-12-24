package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.core.AppResult
import com.survivalcoding.gangnam2kiandroidstudy.core.NetworkError
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeSearchCondition
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository

object MockErrorRecipeRepositoryImpl : RecipeRepository {

    override suspend fun getRecipes(searchCondition: RecipeSearchCondition): AppResult<List<Recipe>, NetworkError> {
        return AppResult.Error(NetworkError.Unknown("error message"))
    }

    override suspend fun getSavedRecipes(): AppResult<List<Recipe>, NetworkError> {
        return AppResult.Error(NetworkError.Unknown("error message"))
    }

    override suspend fun getRecipe(recipeId: Long): Recipe {
        throw Exception("Simulated Error")
    }
}

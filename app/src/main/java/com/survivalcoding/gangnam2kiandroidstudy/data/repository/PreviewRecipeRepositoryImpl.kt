package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.core.NetworkError
import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.data.model.RecipeSearchCondition

object PreviewRecipeRepositoryImpl : RecipeRepository {
    val sampleRecipes = List(10) {
        Recipe(
            name = "spice roasted chicken with flavored rice",
            imageUrl = "https://cdn.pixabay.com/photo/2017/11/10/15/04/steak-2936531_1280.jpg",
            chef = "Chef John",
            time = 20,
            rating = 4.0,
        )
    }

    override suspend fun getSavedRecipes(): Result<List<Recipe>, NetworkError> =
        Result.Success(sampleRecipes)

    override suspend fun getRecipes(searchCondition: RecipeSearchCondition): Result<List<Recipe>, NetworkError> =
        Result.Success(sampleRecipes)
}
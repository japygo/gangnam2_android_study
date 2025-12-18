package com.survivalcoding.gangnam2kiandroidstudy.domain.usecase

import com.survivalcoding.gangnam2kiandroidstudy.core.AppResult
import com.survivalcoding.gangnam2kiandroidstudy.core.NetworkError
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeSearchCondition
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.util.NetworkErrorHandler

class GetNewRecipesUseCase(
    private val recipeRepository: RecipeRepository,
) {
    suspend operator fun invoke(): AppResult<List<Recipe>, NetworkError> {
        return NetworkErrorHandler.handle {
            recipeRepository.getRecipes(RecipeSearchCondition())
        }
    }
}
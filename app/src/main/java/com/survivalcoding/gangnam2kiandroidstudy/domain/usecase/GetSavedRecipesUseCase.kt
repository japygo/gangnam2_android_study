package com.survivalcoding.gangnam2kiandroidstudy.domain.usecase

import com.survivalcoding.gangnam2kiandroidstudy.core.AppResult
import com.survivalcoding.gangnam2kiandroidstudy.core.NetworkError
import com.survivalcoding.gangnam2kiandroidstudy.core.util.NetworkErrorHandler
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository

class GetSavedRecipesUseCase(
    private val recipeRepository: RecipeRepository,
    private val bookmarkRepository: BookmarkRepository,
) {
    suspend operator fun invoke(): AppResult<List<Recipe>, NetworkError> {
        return NetworkErrorHandler.handle {
            val profileId = 1L
            val bookmarks = bookmarkRepository.getBookmarks(profileId)
            val bookmarkSet = bookmarks.toSet()

            when (val result = recipeRepository.getSavedRecipes()) {
                is AppResult.Success -> {
                    AppResult.Success(
                        result.data
                            .map { it.copy(isSaved = it.id in bookmarkSet) }
                            .filter { it.isSaved },
                    )
                }
                is AppResult.Error -> result
            }
        }
    }
}
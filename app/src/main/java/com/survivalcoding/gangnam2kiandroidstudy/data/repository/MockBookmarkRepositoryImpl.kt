package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository

object MockBookmarkRepositoryImpl : BookmarkRepository {

    private val bookmarks =
        MockRecipeRepositoryImpl.mockRecipes.map { it.copy(isSaved = true) }.toMutableList()

    override suspend fun toggleBookmark(recipeId: Long) {
        if (bookmarks.any { it.id == recipeId }) {
            bookmarks -= bookmarks.first { it.id == recipeId }
        } else {
            bookmarks += MockRecipeRepositoryImpl.mockRecipes.first { it.id == recipeId }
        }
    }

    override suspend fun getBookmarks(profileId: Long): List<Long> {
        return bookmarks.map { it.id }
    }
}
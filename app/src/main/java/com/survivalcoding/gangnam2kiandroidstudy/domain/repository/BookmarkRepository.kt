package com.survivalcoding.gangnam2kiandroidstudy.domain.repository

interface BookmarkRepository {
    suspend fun toggleBookmark(recipeId: Long)
    suspend fun getBookmarks(profileId: Long): List<Long>
}
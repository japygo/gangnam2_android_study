package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.data.dao.BookmarkDao
import com.survivalcoding.gangnam2kiandroidstudy.data.entity.BookmarkEntity
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository

class RoomBookmarkRepositoryImpl(
    private val bookmarkDao: BookmarkDao,
) : BookmarkRepository {

    override suspend fun toggleBookmark(recipeId: Long) {
        val existingBookmark = bookmarkDao.findByRecipeId(recipeId)
        if (existingBookmark == null) {
            bookmarkDao.insertBookmark(
                BookmarkEntity(
                    recipeId = recipeId,
                    profileId = 1L,
                ),
            )
        } else {
            bookmarkDao.deleteBookmark(existingBookmark)
        }
    }

    override suspend fun getBookmarks(profileId: Long): List<Long> {
        return bookmarkDao.findAllByProfileId(profileId).map { it.recipeId }
    }
}
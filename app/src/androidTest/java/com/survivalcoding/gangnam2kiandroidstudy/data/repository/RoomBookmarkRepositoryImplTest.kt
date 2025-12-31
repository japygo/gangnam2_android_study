package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.survivalcoding.gangnam2kiandroidstudy.data.dao.BookmarkDao
import com.survivalcoding.gangnam2kiandroidstudy.data.database.AppDatabase
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class RoomBookmarkRepositoryImplTest {

    private lateinit var db: AppDatabase
    private lateinit var bookmarkDao: BookmarkDao
    private lateinit var bookmarkRepository: BookmarkRepository

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java,
        ).build()
        bookmarkDao = db.bookmarkDao()
        bookmarkRepository = RoomBookmarkRepositoryImpl(bookmarkDao)
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun toggleBookmark() = runTest {
        val recipeId = 1L

        bookmarkRepository.toggleBookmark(recipeId)

        bookmarkDao.findByRecipeId(recipeId).apply {
            assertNotNull(this)
            assertEquals(recipeId, this?.recipeId)
        }

        bookmarkRepository.toggleBookmark(recipeId)

        bookmarkDao.findByRecipeId(recipeId).apply {
            assertNull(this)
        }
    }

    @Test
    fun getBookmarks() = runTest {
        bookmarkRepository.toggleBookmark(1L)
        bookmarkRepository.toggleBookmark(2L)
        bookmarkRepository.toggleBookmark(3L)

        val profileId = 1L
        bookmarkRepository.getBookmarks(profileId).apply {
            assertEquals(3, this.size)
        }
    }
}
package com.survivalcoding.gangnam2kiandroidstudy.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.survivalcoding.gangnam2kiandroidstudy.data.dao.BookmarkDao
import com.survivalcoding.gangnam2kiandroidstudy.data.entity.BookmarkEntity

@Database(entities = [BookmarkEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookmarkDao(): BookmarkDao
}

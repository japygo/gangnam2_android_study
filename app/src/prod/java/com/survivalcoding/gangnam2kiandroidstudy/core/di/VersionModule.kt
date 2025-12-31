package com.survivalcoding.gangnam2kiandroidstudy.core.di

import androidx.room.Room
import com.survivalcoding.gangnam2kiandroidstudy.core.util.NetworkMonitor
import com.survivalcoding.gangnam2kiandroidstudy.core.util.RealNetworkMonitor
import com.survivalcoding.gangnam2kiandroidstudy.data.dao.BookmarkDao
import com.survivalcoding.gangnam2kiandroidstudy.data.database.AppDatabase
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RoomBookmarkRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val versionModule = module {
    single<NetworkMonitor> { RealNetworkMonitor(androidContext()) }

    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "recipes.db",
        ).build()
    }
    single<BookmarkDao> { get<AppDatabase>().bookmarkDao() }
    single<BookmarkRepository> { RoomBookmarkRepositoryImpl(get()) }
}
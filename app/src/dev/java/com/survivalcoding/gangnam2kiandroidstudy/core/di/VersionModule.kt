package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.core.util.NetworkMonitor
import com.survivalcoding.gangnam2kiandroidstudy.core.util.RealNetworkMonitor
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.MockBookmarkRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val versionModule = module {
    single<NetworkMonitor> { RealNetworkMonitor(androidContext()) }

    single<BookmarkRepository> { MockBookmarkRepositoryImpl }
}
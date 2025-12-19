package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.core.util.NetworkMonitor
import com.survivalcoding.gangnam2kiandroidstudy.core.util.OnlineNetworkMonitor
import org.koin.dsl.module

val versionModule = module {
    single<NetworkMonitor> { OnlineNetworkMonitor }
}
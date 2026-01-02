package com.survivalcoding.gangnam2kiandroidstudy

import android.app.Application
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.survivalcoding.gangnam2kiandroidstudy.core.di.appModule
import com.survivalcoding.gangnam2kiandroidstudy.core.di.dataSourceModule
import com.survivalcoding.gangnam2kiandroidstudy.core.di.repositoryModule
import com.survivalcoding.gangnam2kiandroidstudy.core.di.useCaseModule
import com.survivalcoding.gangnam2kiandroidstudy.core.di.versionModule
import com.survivalcoding.gangnam2kiandroidstudy.core.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.FLAVOR in listOf("dev", "qa")) {
            Firebase.auth.useEmulator("10.0.2.2", 9099)
            Firebase.firestore.useEmulator("10.0.2.2", 9090)
        }

        startKoin {
            androidLogger()
            androidContext(this@AppApplication)
            modules(
                appModule,
                versionModule,
                dataSourceModule,
                repositoryModule,
                useCaseModule,
                viewModelModule,
            )
        }
    }
}
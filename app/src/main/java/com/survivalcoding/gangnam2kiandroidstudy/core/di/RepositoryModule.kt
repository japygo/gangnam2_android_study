package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.data.repository.FirebaseSignUpRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.MockIngredientRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.MockNotificationRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.MockProcedureRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.MockProfileRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.MockRecipeRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.IngredientRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.NotificationRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProcedureRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProfileRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.SignUpRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<RecipeRepository> { MockRecipeRepositoryImpl }
//    single<RecipeRepository> { RecipeRepositoryImpl(get(), get()) }
    single<ProfileRepository> { MockProfileRepositoryImpl }
    single<IngredientRepository> { MockIngredientRepositoryImpl }
    single<ProcedureRepository> { MockProcedureRepositoryImpl }
    single<NotificationRepository> { MockNotificationRepositoryImpl }
    single<SignUpRepository> { FirebaseSignUpRepositoryImpl() }
}
package com.survivalcoding.gangnam2kiandroidstudy.core.di

import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetNewRecipesUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetRecipeDetailsUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetRecipesUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetSavedRecipesUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.SignUpWithEmailUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.SignUpWithGoogleUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.ToggleBookmarkUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetNewRecipesUseCase(get()) }
    single { GetRecipeDetailsUseCase(get(), get(), get(), get()) }
    single { GetRecipesUseCase(get()) }
    single { GetSavedRecipesUseCase(get(), get()) }
    single { ToggleBookmarkUseCase(get()) }
    single { SignUpWithEmailUseCase(get()) }
    single { SignUpWithGoogleUseCase(get()) }
}
package com.survivalcoding.gangnam2kiandroidstudy

import android.app.Application
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.RemoteRecipeDataSourceImpl
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepositoryImpl

class AppApplication : Application() {

    val recipeDataSource: RecipeDataSource by lazy { RemoteRecipeDataSourceImpl() }
    val recipeRepository: RecipeRepository by lazy { RecipeRepositoryImpl(recipeDataSource) }
}
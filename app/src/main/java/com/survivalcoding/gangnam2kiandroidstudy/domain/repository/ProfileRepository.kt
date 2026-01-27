package com.survivalcoding.gangnam2kiandroidstudy.domain.repository

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Profile

interface ProfileRepository {
    suspend fun getProfileByRecipeId(recipeId: Long): Profile
    suspend fun getProfile(profileId: Long): Profile
}
package com.survivalcoding.gangnam2kiandroidstudy.domain.repository

import com.survivalcoding.gangnam2kiandroidstudy.core.AppResult

interface SignUpRepository {
    suspend fun signUpWithEmail(email: String, password: String): AppResult<Unit, String>
    suspend fun signUpWithGoogle(idToken: String): AppResult<Unit, String>
}
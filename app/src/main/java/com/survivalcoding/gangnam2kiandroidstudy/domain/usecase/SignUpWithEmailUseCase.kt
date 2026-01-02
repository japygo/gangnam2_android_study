package com.survivalcoding.gangnam2kiandroidstudy.domain.usecase

import com.survivalcoding.gangnam2kiandroidstudy.core.AppResult
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.SignUpRepository

class SignUpWithEmailUseCase(
    private val signUpRepository: SignUpRepository,
) {
    suspend operator fun invoke(email: String, password: String): AppResult<Unit, String> {
        if (email.isBlank()) return AppResult.Error("Email is empty")
        if (password.isBlank()) return AppResult.Error("Password is empty")

        return signUpRepository.signUpWithEmail(email, password)
    }
}
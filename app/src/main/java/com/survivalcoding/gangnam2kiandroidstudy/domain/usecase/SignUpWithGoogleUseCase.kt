package com.survivalcoding.gangnam2kiandroidstudy.domain.usecase

import com.survivalcoding.gangnam2kiandroidstudy.core.AppResult
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.SignUpRepository

class SignUpWithGoogleUseCase(
    private val signUpRepository: SignUpRepository,
) {
    suspend operator fun invoke(idToken: String): AppResult<Unit, String> {
        if (idToken.isBlank()) return AppResult.Error("idToken is empty")

        return signUpRepository.signUpWithGoogle(idToken)
    }
}
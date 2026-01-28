package com.survivalcoding.gangnam2kiandroidstudy.domain.usecase

import com.survivalcoding.gangnam2kiandroidstudy.core.AppResult
import com.survivalcoding.gangnam2kiandroidstudy.core.NetworkError
import com.survivalcoding.gangnam2kiandroidstudy.core.util.NetworkErrorHandler
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Profile
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.ProfileRepository

class GetProfileUseCase(
    private val profileRepository: ProfileRepository,
) {
    suspend operator fun invoke(profileId: Long): AppResult<Profile, NetworkError> {
        return NetworkErrorHandler.handle {
            val profile = profileRepository.getProfile(profileId)

            AppResult.Success(profile)
        }
    }
}
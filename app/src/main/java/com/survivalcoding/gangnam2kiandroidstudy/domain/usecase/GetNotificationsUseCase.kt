package com.survivalcoding.gangnam2kiandroidstudy.domain.usecase

import com.survivalcoding.gangnam2kiandroidstudy.core.AppResult
import com.survivalcoding.gangnam2kiandroidstudy.core.NetworkError
import com.survivalcoding.gangnam2kiandroidstudy.core.util.NetworkErrorHandler
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Notification
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.NotificationSearchCondition
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.NotificationRepository

class GetNotificationsUseCase(
    private val repository: NotificationRepository,
) {
    suspend operator fun invoke(searchCondition: NotificationSearchCondition): AppResult<List<Notification>, NetworkError> {
        return NetworkErrorHandler.handle {
            repository.getNotifications(searchCondition)
        }
    }
}
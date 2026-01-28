package com.survivalcoding.gangnam2kiandroidstudy.domain.repository

import com.survivalcoding.gangnam2kiandroidstudy.core.AppResult
import com.survivalcoding.gangnam2kiandroidstudy.core.NetworkError
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Notification
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.NotificationSearchCondition

interface NotificationRepository {
    suspend fun getNotifications(searchCondition: NotificationSearchCondition): AppResult<List<Notification>, NetworkError>
}
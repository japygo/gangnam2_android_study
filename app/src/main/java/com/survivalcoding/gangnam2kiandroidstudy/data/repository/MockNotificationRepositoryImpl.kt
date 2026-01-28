package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.core.AppResult
import com.survivalcoding.gangnam2kiandroidstudy.core.NetworkError
import com.survivalcoding.gangnam2kiandroidstudy.core.util.minusDays
import com.survivalcoding.gangnam2kiandroidstudy.core.util.minusMinutes
import com.survivalcoding.gangnam2kiandroidstudy.core.util.now
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Notification
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.NotificationFilterType
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.NotificationSearchCondition
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.NotificationRepository
import kotlinx.datetime.LocalDateTime

object MockNotificationRepositoryImpl : NotificationRepository {

    val mockNotification = Notification(
        id = 1,
        title = "New Recipe Alert!",
        description = "Lorem Ipsum tempor incididunt ut labore et dolore,in voluptate velit esse cillum",
        createdAt = LocalDateTime.now(),
    )

    val mockNotifications = listOf(
        mockNotification.copy(
            id = 1,
            createdAt = LocalDateTime.now().minusMinutes(10),
            isRead = false,
            isSaved = false,
        ),
        mockNotification.copy(
            id = 2,
            createdAt = LocalDateTime.now().minusMinutes(20),
            isRead = true,
            isSaved = false,
        ),
        mockNotification.copy(
            id = 3,
            createdAt = LocalDateTime.now().minusMinutes(30),
            isRead = false,
            isSaved = true,
        ),
        mockNotification.copy(
            id = 4,
            createdAt = LocalDateTime.now().minusDays(1).minusMinutes(10),
            isRead = false,
            isSaved = false,
        ),
        mockNotification.copy(
            id = 5,
            createdAt = LocalDateTime.now().minusDays(1).minusMinutes(20),
            isRead = true,
            isSaved = false,
        ),
        mockNotification.copy(
            id = 6,
            createdAt = LocalDateTime.now().minusDays(1).minusMinutes(30),
            isRead = false,
            isSaved = true,
        ),
        mockNotification.copy(
            id = 7,
            createdAt = LocalDateTime.now().minusDays(2).minusMinutes(10),
            isRead = false,
            isSaved = false,
        ),
        mockNotification.copy(
            id = 8,
            createdAt = LocalDateTime.now().minusDays(3).minusMinutes(20),
            isRead = true,
            isSaved = false,
        ),
        mockNotification.copy(
            id = 9,
            createdAt = LocalDateTime.now().minusDays(4).minusMinutes(30),
            isRead = false,
            isSaved = true,
        ),
    )

    override suspend fun getNotifications(searchCondition: NotificationSearchCondition): AppResult<List<Notification>, NetworkError> {
        return when (searchCondition.filterType) {
            NotificationFilterType.ALL -> AppResult.Success(mockNotifications)
            NotificationFilterType.READ -> AppResult.Success(mockNotifications.filter { it.isRead })
            NotificationFilterType.UNREAD -> AppResult.Success(mockNotifications.filter { !it.isRead })
        }
    }
}
package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.notification

import androidx.compose.runtime.Immutable
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Notification
import kotlinx.datetime.LocalDate

@Immutable
data class NotificationUiState(
    val todayNotifications: List<Notification> = emptyList(),
    val yesterdayNotifications: List<Notification> = emptyList(),
    val groupedOtherNotifications: Map<LocalDate, List<Notification>> = emptyMap(),
    val isLoading: Boolean = false,
    val selectedTabIndex: Int = 0,
)
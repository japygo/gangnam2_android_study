package com.survivalcoding.gangnam2kiandroidstudy.domain.model

import kotlinx.datetime.LocalDateTime

data class Notification(
    val id: Long,
    val title: String,
    val description: String,
    val createdAt: LocalDateTime,
    val isRead: Boolean = false,
    val isSaved: Boolean = false,
)

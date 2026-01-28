package com.survivalcoding.gangnam2kiandroidstudy.domain.model

import kotlinx.datetime.LocalDateTime

data class Review(
    val id: Long,
    val content: String,
    val profileId: Long,
    val profileName: String,
    val profileImageUrl: String,
    val createdAt: LocalDateTime,
    val likeCount: Int = 0,
    val dislikeCount: Int = 0,
    val isLiked: Boolean = false,
    val isDisliked: Boolean = false,
)

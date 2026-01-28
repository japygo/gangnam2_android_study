package com.survivalcoding.gangnam2kiandroidstudy.clone.youtube.data.model

data class Video(
    val id: Long,
    val title: String,
    val thumbnailUrl: String,
    val channelName: String,
    val channelImageUrl: String,
    val viewCount: Long,
    val publishedAt: String,
    val duration: String,
)

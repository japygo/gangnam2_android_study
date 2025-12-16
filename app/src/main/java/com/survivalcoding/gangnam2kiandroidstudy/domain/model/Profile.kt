package com.survivalcoding.gangnam2kiandroidstudy.domain.model

data class Profile(
    val id: Long,
    val name: String,
    val imageUrl: String,
    val recipeCount: Int = 0,
    val followerCount: Int = 0,
    val followingCount: Int = 0,
    val job: String = "",
    val biography: String = "",
)

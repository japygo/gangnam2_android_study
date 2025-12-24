package com.survivalcoding.gangnam2kiandroidstudy.domain.model

data class Recipe(
    val id: Long,
    val name: String,
    val imageUrl: String,
    val chef: String,
    val time: Int,
    val rating: Double,
    val serve: Int = 1,
    val chefImageUrl: String = "https://cdn.pixabay.com/photo/2022/10/19/01/02/woman-7531315_1280.png",
    val isSaved: Boolean = false,
)

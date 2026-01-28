package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.reviews

import androidx.compose.runtime.Immutable
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Review

@Immutable
data class ReviewsUiState(
    val reviews: List<Review> = emptyList(),
    val commentCount: Int = 0,
    val savedCount: Int = 0,
    val isLoading: Boolean = false,
)

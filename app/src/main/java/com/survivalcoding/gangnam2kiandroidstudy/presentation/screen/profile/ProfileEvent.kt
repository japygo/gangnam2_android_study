package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.profile

sealed interface ProfileEvent {
    data class NavigateToDetails(val recipeId: Long) : ProfileEvent
}
package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.profile

sealed interface ProfileAction {
    data object OnMoreClick : ProfileAction
    data class OnTabClick(val tabIndex: Int) : ProfileAction
    data class OnCardClick(val recipeId: Long) : ProfileAction
    data class OnBookmarkClick(val recipeId: Long) : ProfileAction
}
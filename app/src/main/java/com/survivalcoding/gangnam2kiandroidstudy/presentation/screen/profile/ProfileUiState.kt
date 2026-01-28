package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.profile

import androidx.compose.runtime.Immutable
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Profile
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe

@Immutable
data class ProfileUiState(
    val profile: Profile? = null,
    val recipes: List<Recipe> = emptyList(),
    val isLoading: Boolean = false,
    val selectedTabIndex: Int = 0,
    val isExpanded: Boolean = false,
)
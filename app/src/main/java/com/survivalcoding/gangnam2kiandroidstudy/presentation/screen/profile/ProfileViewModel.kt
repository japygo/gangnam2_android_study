@file:OptIn(ExperimentalCoroutinesApi::class)

package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.core.AppResult
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetProfileUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetSavedRecipesUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.ToggleBookmarkUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val getSavedRecipesUseCase: GetSavedRecipesUseCase,
    private val toggleBookmarkUseCase: ToggleBookmarkUseCase,
    private val getProfileUseCase: GetProfileUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState = _uiState.asStateFlow()

    private val _event = MutableSharedFlow<ProfileEvent>()
    val event = _event.asSharedFlow()

    init {
        getProfile()

        getSavedRecipesUseCase().mapLatest { result ->
            when (result) {
                is AppResult.Success -> {
                    _uiState.update { it.copy(recipes = result.data) }
                }
                is AppResult.Error -> {
                    _uiState.update { it.copy(recipes = emptyList()) }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onAction(action: ProfileAction) {
        when (action) {
            ProfileAction.OnMoreClick -> toggleMore()
            is ProfileAction.OnTabClick -> changeTab(action.tabIndex)
            is ProfileAction.OnCardClick -> navigateToDetails(action.recipeId)
            is ProfileAction.OnBookmarkClick -> toggleBookmark(action.recipeId)
        }
    }

    private fun getProfile() {
        viewModelScope.launch {
            when (val result = getProfileUseCase(1L)) {
                is AppResult.Success -> {
                    _uiState.update { it.copy(profile = result.data) }
                }
                is AppResult.Error -> Unit
            }
        }
    }

    fun toggleMore() {
        _uiState.update { it.copy(isExpanded = !it.isExpanded) }
    }

    fun changeTab(selectedIndex: Int) {
        _uiState.update { it.copy(selectedTabIndex = selectedIndex) }
    }

    private fun navigateToDetails(recipeId: Long) {
        viewModelScope.launch {
            _event.emit(ProfileEvent.NavigateToDetails(recipeId))
        }
    }

    private fun toggleBookmark(recipeId: Long) {
        viewModelScope.launch {
            when (toggleBookmarkUseCase(recipeId)) {
                is AppResult.Success -> {
                    val recipes = uiState.value.recipes.filterNot { it.id == recipeId }
                    _uiState.update { it.copy(recipes = recipes) }
                }
                is AppResult.Error -> Unit
            }
        }
    }
}
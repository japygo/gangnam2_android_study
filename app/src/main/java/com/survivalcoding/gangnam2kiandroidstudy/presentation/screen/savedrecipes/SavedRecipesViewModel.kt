package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.savedrecipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.core.AppResult
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetSavedRecipesUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.ToggleBookmarkUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SavedRecipesViewModel(
    private val getSavedRecipesUseCase: GetSavedRecipesUseCase,
    private val toggleBookmarkUseCase: ToggleBookmarkUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(SavedRecipesUiState())
    val uiState: StateFlow<SavedRecipesUiState> = _uiState.asStateFlow()

    private val _event = MutableSharedFlow<SavedRecipesEvent>()
    val event = _event.asSharedFlow()

    init {
        getSavedRecipes()
    }

    fun onAction(action: SavedRecipesAction) {
        when (action) {
            is SavedRecipesAction.OnBookmarkClick -> toggleBookmark(action.recipeId)
            is SavedRecipesAction.OnCardClick -> navigateToDetails(action.recipeId)
        }
    }

    private fun getSavedRecipes() {
        viewModelScope.launch {
            when (val result = getSavedRecipesUseCase()) {
                is AppResult.Success -> {
                    _uiState.update { it.copy(recipes = result.data) }
                }

                is AppResult.Error -> {
                    _uiState.update { it.copy(recipes = emptyList()) }
                }
            }
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

    private fun navigateToDetails(recipeId: Long) {
        viewModelScope.launch {
            _event.emit(SavedRecipesEvent.NavigateToDetails(recipeId))
        }
    }
}
package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.core.AppResult
import com.survivalcoding.gangnam2kiandroidstudy.core.util.CopyManager
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetRecipeDetailsUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.ToggleBookmarkUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RecipeDetailsViewModel(
    recipeId: Long,
    private val getRecipeDetailsUseCase: GetRecipeDetailsUseCase,
    private val toggleBookmarkUseCase: ToggleBookmarkUseCase,
    private val copyManager: CopyManager,
) : ViewModel() {
    private val _uiState = MutableStateFlow(RecipeDetailsUiState())
    val uiState: StateFlow<RecipeDetailsUiState> = _uiState.asStateFlow()

    private val _event = MutableSharedFlow<RecipeDetailsEvent>()
    val event = _event.asSharedFlow()

    init {
        fetchRecipeDetails(recipeId)
    }

    fun onAction(action: RecipeDetailsAction) {
        when (action) {
            RecipeDetailsAction.OnBackClick -> navigateToBack()
            is RecipeDetailsAction.OnTabClick -> changeTab(action.tabIndex)
            RecipeDetailsAction.OnMenuClick -> showMenu()
            RecipeDetailsAction.OnMenuDismissRequest -> hideMenu()
            RecipeDetailsAction.OnShareClick -> showShareDialog()
            RecipeDetailsAction.OnShareDismissRequest -> hideShareDialog()
            is RecipeDetailsAction.OnCopyClick -> copy(action.text)
            RecipeDetailsAction.OnRateClick -> showRateDialog()
            is RecipeDetailsAction.OnReviewClick -> navigateToReviews(action.recipeId)
            is RecipeDetailsAction.OnBookmarkClick -> toggleBookmark(action.recipeId)
        }
    }

    private fun fetchRecipeDetails(recipeId: Long) {
        setLoading(true)

        viewModelScope.launch {
            try {
                when (val result = getRecipeDetailsUseCase(recipeId)) {
                    is AppResult.Success -> {
                        _uiState.update {
                            it.copy(
                                recipe = result.data.recipe,
                                profile = result.data.profile,
                                ingredients = result.data.ingredients,
                                procedures = result.data.procedures,
                            )
                        }
                    }
                    is AppResult.Error -> {
                        _uiState.update {
                            it.copy(
                                recipe = null,
                                profile = null,
                                ingredients = emptyList(),
                                procedures = emptyList(),
                            )
                        }
                    }
                }
            } finally {
                setLoading(false)
            }
        }
    }

    private fun changeTab(selectedIndex: Int) {
        _uiState.update { it.copy(selectedTabIndex = selectedIndex) }
    }

    private fun setLoading(isLoading: Boolean) {
        _uiState.update { it.copy(isLoading = isLoading) }
    }

    private fun navigateToBack() {
        viewModelScope.launch {
            _event.emit(RecipeDetailsEvent.NavigateToBack)
        }
    }

    private fun showMenu() {
        _uiState.update {
            it.copy(isMenuVisible = true)
        }
    }

    private fun hideMenu() {
        _uiState.update {
            it.copy(isMenuVisible = false)
        }
    }

    private fun showShareDialog() {
        _uiState.update {
            it.copy(isShareDialogVisible = true)
        }
        hideMenu()
    }

    private fun hideShareDialog() {
        _uiState.update {
            it.copy(isShareDialogVisible = false)
        }
    }

    private fun showRateDialog() {
        _uiState.update {
            it.copy(isRateDialogVisible = true)
        }
        hideMenu()
    }

    private fun navigateToReviews(recipeId: Long) {
        viewModelScope.launch {
            _event.emit(RecipeDetailsEvent.NavigateToReviews(recipeId))
        }
        hideMenu()
    }

    private fun toggleBookmark(recipeId: Long) {
        viewModelScope.launch {
            when (toggleBookmarkUseCase(recipeId)) {
                is AppResult.Success -> {
                    _uiState.update {
                        it.copy(
                            recipe = it.recipe?.copy(
                                isSaved = !it.recipe.isSaved,
                            ),
                        )
                    }
                }
                is AppResult.Error -> Unit
            }
        }
        hideMenu()
    }

    private fun copy(text: String) {
        copyManager.copy(text)
    }
}
@file:OptIn(FlowPreview::class)

package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.core.AppResult
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.CategoryFilterType
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeSearchCondition
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeSearchFilter
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetNewRecipesUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetRecipesUseCase
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.ToggleBookmarkUseCase
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getRecipesUseCase: GetRecipesUseCase,
    private val getNewRecipesUseCase: GetNewRecipesUseCase,
    private val toggleBookmarkUseCase: ToggleBookmarkUseCase,
) : ViewModel() {
    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val queryFlow = uiState.map { it.query }

    init {
        queryFlow.debounce(DEBOUNCE_TIMEOUT_MILLIS)
            .distinctUntilChanged()
            .onEach { fetchRecipes(query = it) }
            .launchIn(viewModelScope)

        fetchRecipes()
        fetchNewRecipes()
    }

    fun onAction(action: HomeAction) {
        when (action) {
            is HomeAction.ChangeQuery -> changeQuery(action.query)
            is HomeAction.SelectCategory -> selectCategory(action.category)
            is HomeAction.ToggleBookmark -> toggleBookmark(action.recipeId)
        }
    }

    private fun fetchRecipes(
        category: CategoryFilterType = uiState.value.category,
        query: String = uiState.value.query,
    ) {
        setLoading(true)

        viewModelScope.launch {
            try {
                val condition =
                    RecipeSearchCondition(query, RecipeSearchFilter(category = category))

                when (val recipes = getRecipesUseCase(condition)) {
                    is AppResult.Success -> {
                        changeRecipes(recipes.data)
                    }
                    is AppResult.Error -> {
                        changeRecipes(emptyList())
                    }
                }
            } finally {
                setLoading(false)
            }
        }
    }

    private fun fetchNewRecipes() {
        setNewRecipeLoading(true)

        viewModelScope.launch {
            try {
                when (val recipes = getNewRecipesUseCase()) {
                    is AppResult.Success -> {
                        changeNewRecipes(recipes.data)
                    }
                    is AppResult.Error -> {
                        changeNewRecipes(emptyList())
                    }
                }
            } finally {
                setNewRecipeLoading(false)
            }
        }
    }

    private fun selectCategory(category: CategoryFilterType) {
        changeCategory(category)
        fetchRecipes(category)
    }

    private fun changeCategory(category: CategoryFilterType) {
        _uiState.update { it.copy(category = category) }
    }

    private fun changeQuery(query: String) {
        _uiState.update { it.copy(query = query) }
    }

    private fun toggleBookmark(recipeId: Long) {
        viewModelScope.launch {
            when (toggleBookmarkUseCase(recipeId)) {
                is AppResult.Success -> {
                    val recipes = uiState.value.recipes.map { recipe ->
                        if (recipe.id == recipeId) {
                            recipe.copy(isSaved = !recipe.isSaved)
                        } else {
                            recipe
                        }
                    }
                    _uiState.update { it.copy(recipes = recipes) }
                }
                is AppResult.Error -> Unit
            }
        }
    }

    private fun changeRecipes(recipes: List<Recipe>) {
        _uiState.update { it.copy(recipes = recipes) }
    }

    private fun setLoading(isLoading: Boolean) {
        _uiState.update { it.copy(isLoading = isLoading) }
    }

    private fun changeNewRecipes(recipes: List<Recipe>) {
        _uiState.update { it.copy(newRecipes = recipes) }
    }

    private fun setNewRecipeLoading(isLoading: Boolean) {
        _uiState.update { it.copy(isNewRecipesLoading = isLoading) }
    }

    companion object {
        const val DEBOUNCE_TIMEOUT_MILLIS = 500L
    }
}
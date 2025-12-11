package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.survivalcoding.gangnam2kiandroidstudy.AppApplication
import com.survivalcoding.gangnam2kiandroidstudy.core.AppResult
import com.survivalcoding.gangnam2kiandroidstudy.data.model.CategoryFilterType
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.data.model.RecipeSearchCondition
import com.survivalcoding.gangnam2kiandroidstudy.data.model.RecipeSearchFilter
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepository
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

@FlowPreview
class HomeViewModel(
    private val recipeRepository: RecipeRepository,
    homeUiState: HomeUiState = HomeUiState(),
) : ViewModel() {
    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(homeUiState)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val queryFlow = uiState.map { it.query }

    init {
        queryFlow.debounce(DEBOUNCE_TIMEOUT_MILLIS)
            .distinctUntilChanged()
            .onEach { fetchRecipes(query = it) }
            .launchIn(viewModelScope)
    }

    fun fetchRecipes(
        category: CategoryFilterType = uiState.value.category,
        query: String = uiState.value.query,
    ) {
        setLoading(true)

        viewModelScope.launch {
            try {
                val condition =
                    RecipeSearchCondition(query, RecipeSearchFilter(category = category))

                when (val recipes = recipeRepository.getRecipes(condition)) {
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

    fun selectCategory(category: CategoryFilterType) {
        changeCategory(category)
        fetchRecipes(category)
    }

    fun changeCategory(category: CategoryFilterType) {
        _uiState.update { it.copy(category = category) }
    }

    fun changeQuery(query: String) {
        _uiState.update { it.copy(query = query) }
    }

    private fun changeRecipes(recipes: List<Recipe>) {
        _uiState.update { it.copy(recipes = recipes) }
    }

    private fun setLoading(isLoading: Boolean) {
        _uiState.update { it.copy(isLoading = isLoading) }
    }

    companion object {
        const val DEBOUNCE_TIMEOUT_MILLIS = 500L

        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val repository = (this[APPLICATION_KEY] as AppApplication).recipeRepository
                HomeViewModel(repository)
            }
        }
    }
}
package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.searchrecipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.survivalcoding.gangnam2kiandroidstudy.AppApplication
import com.survivalcoding.gangnam2kiandroidstudy.core.AppResult
import com.survivalcoding.gangnam2kiandroidstudy.data.model.RecipeSearchCondition
import com.survivalcoding.gangnam2kiandroidstudy.data.model.RecipeSearchFilter
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
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
class SearchRecipesViewModel(
    private val repository: RecipeRepository,
    state: SearchRecipesUiState = SearchRecipesUiState(),
) : ViewModel() {
    private val _uiState = MutableStateFlow(state)
    val uiState: StateFlow<SearchRecipesUiState> = _uiState.asStateFlow()

    private val searchTextFlow: Flow<String> = uiState.map { it.searchText }

    init {
        searchTextFlow
            .debounce(DEBOUNCE_TIMEOUT_MILLIS)
            .distinctUntilChanged()
            .onEach { fetchRecipes(searchText = it) }
            .launchIn(viewModelScope)
    }

    fun fetchRecipes(
        searchText: String = uiState.value.searchText,
        searchFilter: RecipeSearchFilter = uiState.value.searchFilter,
    ) {
        setLoading(true)

        val condition = RecipeSearchCondition(searchText, searchFilter)

        val isSearched = searchText.isNotBlank() || searchFilter.isNotEmpty()

        viewModelScope.launch {
            try {
                when (val result = repository.getRecipes(condition)) {
                    is AppResult.Success -> {
                        _uiState.update {
                            it.copy(
                                recipes = result.data,
                                isSearched = isSearched,
                            )
                        }
                    }

                    is AppResult.Error -> {
                        _uiState.update {
                            it.copy(
                                recipes = emptyList(),
                                isSearched = isSearched,
                            )
                        }
                    }
                }
            } finally {
                setLoading(false)
            }
        }
    }

    fun changeSearchText(searchText: String) {
        _uiState.update {
            it.copy(searchText = searchText)
        }
    }

    fun showBottomSheet() {
        _uiState.update {
            it.copy(isSheetVisible = true)
        }
    }

    fun hideBottomSheet() {
        _uiState.update {
            it.copy(isSheetVisible = false)
        }
    }

    fun changeSearchFilter(searchFilter: RecipeSearchFilter) {
        _uiState.update {
            it.copy(searchFilter = searchFilter)
        }
    }

    fun applyFilter() {
        fetchRecipes()
        hideBottomSheet()
    }

    private fun setLoading(isLoading: Boolean) {
        _uiState.update {
            it.copy(isLoading = isLoading)
        }
    }

    companion object {
        const val DEBOUNCE_TIMEOUT_MILLIS = 500L

        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val repository = (this[APPLICATION_KEY] as AppApplication).recipeRepository
                SearchRecipesViewModel(repository, SearchRecipesUiState())
            }
        }
    }
}
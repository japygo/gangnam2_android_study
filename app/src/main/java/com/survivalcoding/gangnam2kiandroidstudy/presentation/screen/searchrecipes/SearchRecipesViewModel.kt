package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.searchrecipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.survivalcoding.gangnam2kiandroidstudy.AppApplication
import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.core.Result.Success
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
            .onEach {
                fetchRecipes(it)
            }
            .launchIn(viewModelScope)
    }

    fun fetchRecipes(searchText: String) {
        setLoading(true)

        val isSearched = searchText.isNotBlank()

        viewModelScope.launch {
            when (val result = repository.getRecipes(searchText)) {
                is Success -> {
                    _uiState.update {
                        if (isSearched) it.copy(searchRecipes = result.data)
                        else it.copy(recipes = result.data)
                    }
                    setLoading(false)
                }

                is Result.Error -> {
                    _uiState.update {
                        if (isSearched) it.copy(searchRecipes = emptyList())
                        else it.copy(recipes = emptyList())
                    }
                    setLoading(false)
                }
            }
        }
    }

    fun onSearchTextChange(searchText: String) {
        _uiState.update {
            it.copy(searchText = searchText)
        }
    }

    fun setLoading(isLoading: Boolean) {
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
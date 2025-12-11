package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.savedrecipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.survivalcoding.gangnam2kiandroidstudy.AppApplication
import com.survivalcoding.gangnam2kiandroidstudy.core.AppResult
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SavedRecipesViewModel(
    private val repository: RecipeRepository,
) : ViewModel() {
    private val _recipes: MutableStateFlow<List<Recipe>> = MutableStateFlow(emptyList())
    val recipes: StateFlow<List<Recipe>> = _recipes.asStateFlow()

    init {
        getSavedRecipes()
    }

    fun getSavedRecipes() {
        viewModelScope.launch {
            when (val result = repository.getSavedRecipes()) {
                is AppResult.Success -> {
                    _recipes.value = result.data
                }

                is AppResult.Error -> {
                    _recipes.value = emptyList()
                }
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val repository = (this[APPLICATION_KEY] as AppApplication).recipeRepository
                SavedRecipesViewModel(repository)
            }
        }
    }
}
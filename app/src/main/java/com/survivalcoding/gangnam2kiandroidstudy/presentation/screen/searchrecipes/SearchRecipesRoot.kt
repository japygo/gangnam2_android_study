package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.searchrecipes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.FilterSearchBottomSheet
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchRecipesRoot(
    viewModel: SearchRecipesViewModel = koinViewModel(),
    onRecipeClick: (Long) -> Unit = {},
    onBackClick: () -> Unit = {},
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SearchRecipesScreen(
        uiState = uiState,
        onAction = viewModel::onAction,
        onNavigate = {
            when (it) {
                is SearchRecipeNavigation.OnRecipeClick -> onRecipeClick(it.recipeId)
                SearchRecipeNavigation.OnBackClick -> onBackClick()
            }
        },
    )

    FilterSearchBottomSheet(
        isSheetVisible = uiState.isSheetVisible,
        searchFilter = uiState.searchFilter,
        onAction = viewModel::onAction,
    )
}
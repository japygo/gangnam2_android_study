package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.savedrecipes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun SavedRecipesRoot(
    modifier: Modifier = Modifier,
    viewModel: SavedRecipesViewModel = koinViewModel(),
    onNavigate: (SavedRecipesNavigation) -> Unit = {},
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel.event) {
        viewModel.event.collect { event ->
            when (event) {
                is SavedRecipesEvent.NavigateToDetails -> onNavigate(
                    SavedRecipesNavigation.RecipeDetails(event.recipeId),
                )
            }
        }
    }

    SavedRecipesScreen(
        modifier = modifier,
        uiState = uiState,
        onAction = viewModel::onAction,
    )
}
package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.savedrecipes

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.distinctUntilChanged
import org.koin.androidx.compose.koinViewModel

@Composable
fun SavedRecipesRoot(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState,
    viewModel: SavedRecipesViewModel = koinViewModel(),
    onNavigate: (SavedRecipesNavigation) -> Unit = {},
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val lazyListState = rememberLazyListState()

    LaunchedEffect(viewModel.event) {
        viewModel.event.collect { event ->
            when (event) {
                is SavedRecipesEvent.NavigateToDetails -> onNavigate(
                    SavedRecipesNavigation.RecipeDetails(event.recipeId),
                )
            }
        }
    }

    LaunchedEffect(lazyListState) {
        snapshotFlow { lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .distinctUntilChanged()
            .collect {
                if (it != null && uiState.recipes.size >= 5 && it == uiState.recipes.size) {
                    snackbarHostState.showSnackbar("마지막 데이터입니다.")
                }
            }
    }

    SavedRecipesScreen(
        modifier = modifier,
        lazyListState = lazyListState,
        uiState = uiState,
        onAction = viewModel::onAction,
    )
}
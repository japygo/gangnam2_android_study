package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeRoot(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel(),
    onSearchInputClick: () -> Unit = {},
    onRecipeClick: (Long) -> Unit = {},
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        modifier = modifier,
        uiState = uiState,
        onAction = viewModel::onAction,
        onNavigate = {
            when (it) {
                HomeNavigation.OnSearchInputClick -> {
                    onSearchInputClick()
                }
                is HomeNavigation.OnRecipeClick -> {
                    onRecipeClick(it.recipeId)
                }
            }
        },
    )
}
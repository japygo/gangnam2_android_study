package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipedetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.survivalcoding.gangnam2kiandroidstudy.AppApplication

@Composable
fun RecipeDetailsRoot(
    id: Long,
    modifier: Modifier = Modifier,
    viewModel: RecipeDetailsViewModel = viewModel(
        factory = RecipeDetailsViewModel.factory(
            LocalContext.current.applicationContext as AppApplication,
        ),
    ),
    onBackClick: () -> Unit = {},
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    viewModel.fetchRecipeDetails(id)

    RecipeDetailsScreen(
        modifier = modifier,
        uiState = uiState,
        onTabClick = viewModel::changeTab,
        onBackClick = onBackClick,
    )
}
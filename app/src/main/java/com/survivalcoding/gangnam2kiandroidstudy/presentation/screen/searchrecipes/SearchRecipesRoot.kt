package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.searchrecipes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.FilterSearchBottomSheet

@Composable
fun SearchRecipesRoot(
    viewModel: SearchRecipesViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SearchRecipesScreen(
        uiState = uiState,
        onSearchTextChange = viewModel::changeSearchText,
        onFilterClick = viewModel::showBottomSheet,
    )

    FilterSearchBottomSheet(
        isSheetVisible = uiState.isSheetVisible,
        searchFilter = uiState.searchFilter,
        onDismissRequest = viewModel::hideBottomSheet,
        onFilterChange = viewModel::changeSearchFilter,
        onFilter = viewModel::applyFilter,
    )
}
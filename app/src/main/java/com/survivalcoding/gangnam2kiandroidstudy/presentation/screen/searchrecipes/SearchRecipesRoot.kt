@file:OptIn(ExperimentalMaterial3Api::class)

package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.searchrecipes

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.FilterSearchBottomSheet
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchRecipesRoot(
    viewModel: SearchRecipesViewModel = koinViewModel(),
    onNavigate: (SearchRecipeNavigation) -> Unit = {},
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(viewModel.event) {
        viewModel.event.collect { event ->
            when (event) {
                is SearchRecipesEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(event.message)
                }
                is SearchRecipesEvent.NavigateToDetails -> {
                    onNavigate(SearchRecipeNavigation.RecipeDetails(event.recipeId))
                }
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.search_recipes_title),
                        style = AppTextStyles.PoppinsMediumBold,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onNavigate(SearchRecipeNavigation.Back) }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "back icon",
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = AppColors.White,
                ),
            )
        },
    ) { innerPadding ->
        SearchRecipesScreen(
            modifier = Modifier.padding(innerPadding),
            uiState = uiState,
            onAction = viewModel::onAction,
        )
    }

    FilterSearchBottomSheet(
        isSheetVisible = uiState.isSheetVisible,
        searchFilter = uiState.searchFilter,
        onAction = viewModel::onAction,
    )
}
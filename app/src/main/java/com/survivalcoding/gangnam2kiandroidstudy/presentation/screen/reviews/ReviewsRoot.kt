package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.reviews

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ReviewsRoot(
    id: Long,
    modifier: Modifier = Modifier,
    viewModel: ReviewsViewModel = viewModel(),
    onBackClick: () -> Unit = {},
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(id) {
        viewModel.fetchReviews(id)
    }

    ReviewsScreen(
        modifier = modifier,
        uiState = uiState,
        onBackClick = onBackClick,
    )
}
package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.notification

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun NotificationRoot(
    modifier: Modifier = Modifier,
    viewModel: NotificationViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    NotificationScreen(
        modifier = modifier,
        uiState = uiState,
        onTabClick = viewModel::changeTab,
    )
}
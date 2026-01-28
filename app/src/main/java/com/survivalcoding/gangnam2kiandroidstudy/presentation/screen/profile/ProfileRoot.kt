@file:OptIn(ExperimentalMaterial3Api::class)

package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.profile

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileRoot(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = koinViewModel(),
    onNavigate: (ProfileNavigation) -> Unit = {},
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel.event) {
        viewModel.event.collect { event ->
            when (event) {
                is ProfileEvent.NavigateToDetails -> {
                    onNavigate(ProfileNavigation.RecipeDetails(event.recipeId))
                }
            }
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Profile",
                        style = AppTextStyles.PoppinsMediumBold,
                    )
                },
                actions = {
                    Icon(
                        painter = painterResource(R.drawable.outline_more),
                        contentDescription = "more icon",
                        modifier = Modifier.padding(end = 30.dp),
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = AppColors.White,
                ),
            )
        },
        containerColor = AppColors.White,
    ) { innerPadding ->
        ProfileScreen(
            modifier = modifier.padding(top = innerPadding.calculateTopPadding(), bottom = 0.dp),
            uiState = uiState,
            onAction = viewModel::onAction,
        )
    }
}
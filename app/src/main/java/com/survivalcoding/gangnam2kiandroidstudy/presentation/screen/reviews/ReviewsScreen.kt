@file:OptIn(ExperimentalMaterial3Api::class)

package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.reviews

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Review
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.InputField
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.ReviewCard
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles
import kotlinx.datetime.LocalDateTime

@Composable
fun ReviewsScreen(
    modifier: Modifier = Modifier,
    uiState: ReviewsUiState = ReviewsUiState(),
    onBackClick: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Reviews",
                        style = AppTextStyles.PoppinsMediumBold,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
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
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(color = AppColors.White)
                .padding(innerPadding)
                .padding(horizontal = 30.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "${uiState.commentCount} Comments",
                    style = AppTextStyles.PoppinsSmallerRegular,
                    color = AppColors.Gray3,
                )
                Text(
                    text = "${uiState.savedCount} Saved",
                    style = AppTextStyles.PoppinsSmallerRegular,
                    color = AppColors.Gray3,
                )
            }

            InputField(
                modifier = Modifier.fillMaxWidth(),
                label = "Leave a comment",
                placeholder = "Say something...",
                hasButton = true,
                value = "",
                onValueChange = {},
                onClick = {},
            )

            LazyColumn(
                modifier = Modifier.padding(top = 24.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                items(
                    items = uiState.reviews,
                    key = { it.id },
                ) {
                    ReviewCard(
                        review = it,
                        onLikeClick = {},
                        onDislikeClick = {},
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ReviewsScreenPreview() {
    ReviewsScreen(
        uiState = ReviewsUiState(
            reviews = List(10) {
                Review(
                    id = it.toLong(),
                    content = "Lorem Ipsum tempor incididunt ut labore et dolore,inise voluptate velit esse cillum",
                    profileId = 1,
                    profileName = "Bella Throne",
                    profileImageUrl = "",
                    createdAt = LocalDateTime(2020, 6, 12, 19, 35),
                    likeCount = 9,
                    dislikeCount = 2,
                    isLiked = true,
                    isDisliked = false,
                )
            },
            commentCount = 200,
            savedCount = 155,
        ),
    )
}
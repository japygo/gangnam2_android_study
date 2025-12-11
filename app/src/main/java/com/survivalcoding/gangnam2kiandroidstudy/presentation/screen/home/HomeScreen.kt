package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.data.model.CategoryFilterType
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Profile
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.MockRecipeRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.DishCard
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.RecipeCategorySelector
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.SearchInputField
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles
import com.survivalcoding.gangnam2kiandroidstudy.util.orPreview

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: HomeUiState = HomeUiState(),
    onQueryChange: (String) -> Unit = {},
    onFilterClick: () -> Unit = {},
    onCategorySelect: (CategoryFilterType) -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .statusBarsPadding()
            .navigationBarsPadding(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(top = 20.dp, bottom = 30.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 80.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp),
            ) {
                Text(
                    text = "Hello ${uiState.profile?.name ?: ""}",
                    style = AppTextStyles.PoppinsLargeBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = "What are you cooking today?",
                    style = AppTextStyles.PoppinsSmallerRegular,
                )
            }
            uiState.profile?.let { profile ->
                AsyncImage(
                    model = profile.imageUrl.orPreview,
                    contentDescription = "profile image",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Crop,
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SearchInputField(
                value = uiState.query,
                onValueChange = onQueryChange,
                placeholder = "Search recipe",
                modifier = Modifier.weight(1f),
            )

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .clickable(onClick = onFilterClick)
                    .background(
                        color = AppColors.Primary100,
                        shape = RoundedCornerShape(10.dp),
                    )
                    .padding(10.dp),
            ) {
                Icon(
                    painter = painterResource(R.drawable.outline_setting_2),
                    contentDescription = "filter icon",
                    tint = AppColors.White,
                    modifier = Modifier.size(20.dp),
                )
            }
        }

        RecipeCategorySelector(
            category = uiState.category,
            onCategorySelect = onCategorySelect,
            modifier = Modifier
                .padding(start = 30.dp)
                .padding(vertical = 15.dp),
        )

        if (uiState.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(231.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text("Loading...")
            }
        } else if (uiState.recipes.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(231.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text("데이터가 없습니다.")
            }
        } else {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp),
                horizontalArrangement = Arrangement.spacedBy(15.dp),
            ) {
                items(items = uiState.recipes) {
                    DishCard(recipe = it)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        uiState = HomeUiState(
            recipes = MockRecipeRepositoryImpl.mockRecipes,
            profile = Profile(
                name = "Jane",
                imageUrl = "",
            ),
        ),
    )
}

@Preview(showBackground = true)
@Composable
fun NoProfileHomeScreenPreview() {
    HomeScreen()
}

@Preview(showBackground = true)
@Composable
fun LongNameHomeScreenPreview() {
    HomeScreen(
        uiState = HomeUiState(
            recipes = MockRecipeRepositoryImpl.mockRecipes,
            profile = Profile(
                name = "Jane".repeat(10),
                imageUrl = "",
            ),
        ),
    )
}

@Preview(showBackground = true)
@Composable
fun LoadingHomeScreenPreview() {
    HomeScreen(
        uiState = HomeUiState(
            isLoading = true,
        ),
    )
}
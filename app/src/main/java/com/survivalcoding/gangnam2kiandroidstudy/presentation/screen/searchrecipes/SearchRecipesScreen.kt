package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.searchrecipes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.MockRecipeRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.RecipeCard
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.RecipeCardSize
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.SearchInputField
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun SearchRecipesScreen(
    modifier: Modifier = Modifier,
    uiState: SearchRecipesUiState = SearchRecipesUiState(),
    onSearchTextChange: (String) -> Unit = {},
    onFilterClick: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = AppColors.White)
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Search recipes",
            style = AppTextStyles.PoppinsMediumBold,
            modifier = Modifier.padding(top = 10.dp, bottom = 17.dp),
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SearchInputField(
                value = uiState.searchText,
                onValueChange = onSearchTextChange,
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
                    contentDescription = "setting icon",
                    tint = AppColors.White,
                    modifier = Modifier.size(20.dp),
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = if (uiState.isSearched) "Search Result" else "Recent Search",
                style = AppTextStyles.PoppinsNormalBold,
            )

            if (uiState.isSearched) {
                Text(
                    text = "${uiState.recipes.size} results",
                    style = AppTextStyles.PoppinsSmallerRegular.copy(color = AppColors.Gray3),
                )
            }
        }

        if (uiState.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Text("Loading...")
            }
        } else if (uiState.recipes.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Text("데이터가 없습니다.")
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(15.dp),
                horizontalArrangement = Arrangement.spacedBy(15.dp),
            ) {
                items(items = uiState.recipes) {
                    RecipeCard(
                        recipe = it,
                        size = RecipeCardSize.Small,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchRecipesScreenPreview() {
    SearchRecipesScreen(
        uiState = SearchRecipesUiState(
            recipes = MockRecipeRepositoryImpl.mockRecipes,
        ),
    )
}

@Preview(showBackground = true)
@Composable
fun SearchedSearchRecipesScreenPreview() {
    SearchRecipesScreen(
        uiState = SearchRecipesUiState(
            searchText = "rice",
            recipes = MockRecipeRepositoryImpl.mockRecipes,
        ),
    )
}

@Preview(showBackground = true)
@Composable
fun LoadingSearchRecipesScreenPreview() {
    SearchRecipesScreen(
        uiState = SearchRecipesUiState(
            isLoading = true,
        ),
    )
}
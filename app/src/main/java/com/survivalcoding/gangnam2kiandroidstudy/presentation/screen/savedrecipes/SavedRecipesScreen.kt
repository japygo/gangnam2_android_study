package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.savedrecipes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.MockRecipeRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.RecipeCard
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun SavedRecipesScreen(
    modifier: Modifier = Modifier,
    uiState: SavedRecipesUiState = SavedRecipesUiState(),
    onCardClick: (Long) -> Unit = {},
    onBookmarkClick: (Long) -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = AppColors.White)
            .statusBarsPadding()
            .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Saved Recipes",
            style = AppTextStyles.PoppinsMediumBold,
            modifier = Modifier.padding(vertical = 10.dp),
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            items(
                items = uiState.recipes,
                key = { it.id },
            ) {
                RecipeCard(
                    recipe = it,
                    onClick = onCardClick,
                    onBookmarkClick = onBookmarkClick,
                )
            }
            item { Spacer(modifier = Modifier.height(6.dp)) }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SavedRecipesScreenPreview() {
    SavedRecipesScreen(
        uiState = SavedRecipesUiState(
            recipes = MockRecipeRepositoryImpl.mockRecipes,
        ),
    )
}
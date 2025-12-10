package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.searchrecipes

import androidx.compose.foundation.background
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.core.NetworkError
import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.RecipeRepository
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.RecipeCard
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.RecipeCardSize
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.SearchInputField
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles
import kotlinx.coroutines.FlowPreview

@FlowPreview
@Composable
fun SearchRecipesScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchRecipesViewModel = viewModel(factory = SearchRecipesViewModel.Factory),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val isSearched = uiState.searchText.isNotBlank()

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
                onValueChange = {
                    viewModel.onSearchTextChange(it)
                },
                placeholder = "Search recipe",
                modifier = Modifier.weight(1f),
            )

            Box(
                modifier = Modifier
                    .size(40.dp)
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
                text = if (isSearched) "Search Result" else "Recent Search",
                style = AppTextStyles.PoppinsNormalBold,
            )

            if (isSearched) {
                Text(
                    text = "${uiState.searchRecipes.size} results",
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
        } else {
            LazyVerticalGrid(
                columns = GridCells.FixedSize(150.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp),
                horizontalArrangement = Arrangement.spacedBy(15.dp),
            ) {
                val recipes = if (isSearched) uiState.searchRecipes else uiState.recipes
                items(recipes) {
                    RecipeCard(
                        recipe = it,
                        size = RecipeCardSize.Small,
                    )
                }
            }
        }
    }
}

@FlowPreview
@Preview(showBackground = true)
@Composable
fun SearchRecipesScreenPreview() {
    val viewModel = SearchRecipesViewModel(repository)
    viewModel.fetchRecipes("")
    SearchRecipesScreen(viewModel = viewModel)
}

@FlowPreview
@Preview(showBackground = true)
@Composable
fun SearchedSearchRecipesScreenPreview() {
    val viewModel = SearchRecipesViewModel(
        repository = repository,
        state = SearchRecipesUiState(
            searchText = "rice",
        ),
    )
    viewModel.fetchRecipes("rice")
    SearchRecipesScreen(viewModel = viewModel)
}

@FlowPreview
@Preview(showBackground = true)
@Composable
fun LoadingSearchRecipesScreenPreview() {
    val viewModel = SearchRecipesViewModel(
        repository = repository,
        state = SearchRecipesUiState(
            isLoading = true,
        ),
    )
    SearchRecipesScreen(viewModel = viewModel)
}

private val repository = object : RecipeRepository {
    override suspend fun getSavedRecipes(): Result<List<Recipe>, NetworkError> {
        return Result.Success(
            List(10) {
                Recipe(
                    name = "spice roasted chicken with flavored rice",
                    imageUrl = "https://cdn.pixabay.com/photo/2017/11/10/15/04/steak-2936531_1280.jpg",
                    chef = "Chef John",
                    time = 20,
                    rating = 4.0,
                )
            },
        )
    }

    override suspend fun getRecipes(searchText: String): Result<List<Recipe>, NetworkError> {
        return Result.Success(
            List(10) {
                Recipe(
                    name = "Traditional spare ribs baked",
                    imageUrl = "https://cdn.pixabay.com/photo/2017/11/10/15/04/steak-2936531_1280.jpg",
                    chef = "Chef John",
                    time = 30,
                    rating = 4.5,
                )
            },
        )
    }
}
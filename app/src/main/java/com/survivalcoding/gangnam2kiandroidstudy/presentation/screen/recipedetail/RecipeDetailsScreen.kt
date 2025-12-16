package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipedetail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Ingredient
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Procedure
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Profile
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.IngredientItem
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.ProcedureItem
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.RecipeCard
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.RecipeCardSize
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.SmallButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.Tabs
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles
import com.survivalcoding.gangnam2kiandroidstudy.util.formatCompactNumber
import com.survivalcoding.gangnam2kiandroidstudy.util.orPreview

@Composable
fun RecipeDetailsScreen(
    modifier: Modifier = Modifier,
    uiState: RecipeDetailsUiState = RecipeDetailsUiState(),
    onTabClick: (Int) -> Unit = {},
    onBackClick: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp)
            .statusBarsPadding()
            .navigationBarsPadding(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Icon(
                painter = painterResource(R.drawable.outline_arrow_right),
                contentDescription = "back icon",
                modifier = Modifier
                    .size(20.dp)
                    .rotate(180f)
                    .clickable { onBackClick() },
            )
            Icon(
                painter = painterResource(R.drawable.outline_more),
                contentDescription = "more icon",
                modifier = Modifier
                    .size(24.dp),
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            uiState.recipe?.let {
                RecipeCard(
                    recipe = it,
                    size = RecipeCardSize.Large,
                )
            }

            Row(
                modifier = Modifier.padding(horizontal = 5.dp),
            ) {
                Text(
                    text = uiState.recipe?.name ?: "",
                    style = AppTextStyles.PoppinsSmallBold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 18.dp),
                )

                Text(
                    text = "(${uiState.reviewCount.formatCompactNumber()} Reviews)",
                    style = AppTextStyles.PoppinsSmallBold.copy(AppColors.Gray3),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, end = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AsyncImage(
                    model = uiState.profile?.imageUrl?.orPreview,
                    contentDescription = "profile image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape),
                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(2.dp),
                ) {
                    Text(
                        text = uiState.profile?.name ?: "",
                        style = AppTextStyles.PoppinsSmallBold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(1.dp),
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.bold_location),
                            contentDescription = "location icon",
                            tint = AppColors.Primary80,
                            modifier = Modifier.size(17.dp),
                        )
                        Text(
                            text = uiState.profile?.address ?: "",
                            style = AppTextStyles.PoppinsSmallBold.copy(AppColors.Gray3),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                }
            }

            SmallButton(
                text = "Follow",
                modifier = Modifier.width(100.dp),
            ) {}
        }

        Tabs(
            labels = listOf("Ingredient", "Procedure"),
            selectedIndex = uiState.selectedTabIndex,
            onValueChange = onTabClick,
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 22.dp, bottom = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(R.drawable.outline_serve),
                    contentDescription = "timer icon",
                    tint = AppColors.Gray3,
                    modifier = Modifier.size(17.dp),
                )
                Text(
                    text = "${uiState.recipe?.serve} serve",
                    style = AppTextStyles.PoppinsSmallerRegular.copy(AppColors.Gray3),
                )
            }

            if (uiState.selectedTabIndex == 0) {
                Text(
                    text = "${uiState.ingredients.size} items",
                    style = AppTextStyles.PoppinsSmallerRegular.copy(AppColors.Gray3),
                )
            }

            if (uiState.selectedTabIndex == 1) {
                Text(
                    text = "${uiState.procedures.size} steps",
                    style = AppTextStyles.PoppinsSmallerRegular.copy(AppColors.Gray3),
                )
            }
        }

        if (uiState.selectedTabIndex == 0 && uiState.ingredients.isNotEmpty()) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                items(
                    items = uiState.ingredients,
                    key = { it.name },
                ) {
                    IngredientItem(ingredient = it)
                }
            }
        }

        if (uiState.selectedTabIndex == 1 && uiState.procedures.isNotEmpty()) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                items(
                    items = uiState.procedures,
                    key = { it.step },
                ) {
                    ProcedureItem(procedure = it)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RecipeDetailsScreenPreview() {
    val recipe = Recipe(
        id = 1L,
        name = "spice roasted chicken with flavored rice",
        imageUrl = "https://cdn.pixabay.com/photo/2017/11/10/15/04/steak-2936531_1280.jpg",
        chef = "Chef John",
        time = 20,
        rating = 4.0,
    )
    val profile = Profile(
        id = 1,
        name = "Afuwape Abiodun",
        imageUrl = "https://picsum.photos/id/259/200/300",
        address = "Lagos, Nigeria",
    )
    val ingredients = listOf(
        Ingredient(
            name = "Tomato",
            imageUrl = "",
            amount = "500g",
        ),
        Ingredient(
            name = "Cabbage",
            imageUrl = "",
            amount = "300g",
        ),
        Ingredient(
            name = "Taco",
            imageUrl = "",
            amount = "300g",
        ),
        Ingredient(
            name = "Slice Bread",
            imageUrl = "",
            amount = "300g",
        ),
    )
    val procedures = listOf(
        Procedure(
            recipeId = 1,
            step = 1,
            content = "Lorem Ipsum tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?",
        ),
        Procedure(
            recipeId = 1,
            step = 2,
            content = "Lorem Ipsum tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?\n" +
                    "Tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?",
        ),
        Procedure(
            recipeId = 1,
            step = 3,
            content = "Lorem Ipsum tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?",
        ),
    )
    RecipeDetailsScreen(
        uiState = RecipeDetailsUiState(
            selectedTabIndex = 0,
            recipe = recipe,
            reviewCount = 13_000,
            profile = profile,
            ingredients = ingredients,
            procedures = procedures,
        ),
    )
}
package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.survivalcoding.gangnam2kiandroidstudy.core.util.formatCompactNumber
import com.survivalcoding.gangnam2kiandroidstudy.core.util.orPreview
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.MockRecipeRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Profile
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.RecipeCard
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.Tabs
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    uiState: ProfileUiState = ProfileUiState(),
    onAction: (ProfileAction) -> Unit = {},
) {
    var hasVisualOverflow by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = AppColors.White)
            .padding(top = 10.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            horizontalArrangement = Arrangement.spacedBy(25.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model = uiState.profile?.imageUrl?.orPreview ?: "",
                contentDescription = "profile image",
                modifier = Modifier
                    .size(99.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
            )

            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(2.dp),
                ) {
                    Text(
                        text = "Recipe",
                        style = AppTextStyles.PoppinsSmallerRegular.copy(color = AppColors.Gray3),
                    )
                    Text(
                        text = uiState.profile?.recipeCount?.formatCompactNumber() ?: "0",
                        style = AppTextStyles.PoppinsLargeBold,
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(2.dp),
                ) {
                    Text(
                        text = "Followers",
                        style = AppTextStyles.PoppinsSmallerRegular.copy(color = AppColors.Gray3),
                    )
                    Text(
                        text = uiState.profile?.followerCount?.formatCompactNumber() ?: "0",
                        style = AppTextStyles.PoppinsLargeBold,
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(2.dp),
                ) {
                    Text(
                        text = "Following",
                        style = AppTextStyles.PoppinsSmallerRegular.copy(color = AppColors.Gray3),
                    )
                    Text(
                        text = uiState.profile?.followingCount?.formatCompactNumber() ?: "0",
                        style = AppTextStyles.PoppinsLargeBold,
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 15.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = uiState.profile?.name ?: "no name",
                style = AppTextStyles.PoppinsNormalBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Text(
                text = uiState.profile?.job ?: "",
                style = AppTextStyles.PoppinsSmallerRegular.copy(color = AppColors.Gray3),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Spacer(modifier = Modifier.size(10.dp))

            Column {
                Text(
                    text = uiState.profile?.biography ?: "",
                    style = AppTextStyles.PoppinsSmallerRegular.copy(color = AppColors.Gray2),
                    maxLines = if (uiState.isExpanded) Int.MAX_VALUE else 2,
                    overflow = TextOverflow.Ellipsis,
                    onTextLayout = {
                        if (!uiState.isExpanded) {
                            hasVisualOverflow = it.hasVisualOverflow
                        }
                    },
                )

                if (hasVisualOverflow || uiState.isExpanded) {
                    Text(
                        text = if (uiState.isExpanded) "Show less" else "More...",
                        style = AppTextStyles.PoppinsSmallerRegular.copy(color = AppColors.Primary80),
                        modifier = Modifier.clickable { onAction(ProfileAction.OnMoreClick) },
                    )
                }
            }
        }

        Tabs(
            labels = listOf("Recipe", "Videos", "Tag"),
            selectedIndex = uiState.selectedTabIndex,
            onValueChange = {
                onAction(ProfileAction.OnTabClick(it))
            },
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            items(
                items = uiState.recipes,
                key = { it.id },
            ) { recipe ->
                RecipeCard(
                    recipe = recipe,
                    onClick = {
                        onAction(ProfileAction.OnCardClick(it))
                    },
                    onBookmarkClick = {
                        onAction(ProfileAction.OnBookmarkClick(it))
                    },
                )
            }
            item { Spacer(modifier = Modifier.height(6.dp)) }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileScreenPreview() {
    ProfileScreen(
        uiState = ProfileUiState(
            profile = Profile(
                id = 1,
                name = "Afuwape Abiodun",
                imageUrl = "https://picsum.photos/id/259/200/300",
                recipeCount = 4,
                followerCount = 2_500_000,
                followingCount = 259,
                job = "Chef",
                biography = """
                    Private Chef
                    Passionate about food and life 🥘🍲🍝🍱
                    Passionate about food and life 🥘🍲🍝🍱
                    Passionate about food and life 🥘🍲🍝🍱
                    """.trimIndent(),
            ),
            recipes = MockRecipeRepositoryImpl.mockRecipes,
        ),
    )
}
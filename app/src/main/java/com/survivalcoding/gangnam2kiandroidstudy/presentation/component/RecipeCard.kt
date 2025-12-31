package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.core.util.orPreview
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

enum class RecipeCardSize {
    Small,
    Medium,
    Large,
}

@Composable
fun RecipeCard(
    recipe: Recipe,
    modifier: Modifier = Modifier,
    size: RecipeCardSize = RecipeCardSize.Medium,
    onClick: (Long) -> Unit = {},
    onBookmarkClick: (Long) -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable { onClick(recipe.id) },
    ) {
        AsyncImage(
            model = recipe.imageUrl.orPreview,
            contentDescription = "recipe image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0x00000000),
                            AppColors.Black,
                        ),
                    ),
                ),
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopEnd,
            ) {
                Rating(rating = recipe.rating.toString())
            }

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomStart,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Bottom,
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                    ) {
                        if (size != RecipeCardSize.Large) {
                            Text(
                                text = recipe.name,
                                style = AppTextStyles.PoppinsSmallBold.copy(color = AppColors.White),
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                            )
                            Text(
                                text = "By ${recipe.chef}",
                                style = AppTextStyles.PoppinsSmallerRegular.copy(color = AppColors.White),
                            )
                        }
                    }

                    if (size != RecipeCardSize.Small) {
                        Row(
                            modifier = Modifier.padding(vertical = 4.dp),
                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Image(
                                painter = painterResource(R.drawable.outline_timer),
                                contentDescription = "timer image",
                                colorFilter = ColorFilter.tint(color = AppColors.Gray4),
                                modifier = Modifier.size(17.dp),
                            )
                            Text(
                                text = "${recipe.time} min",
                                style = AppTextStyles.PoppinsSmallerRegular.copy(color = AppColors.Gray4),
                            )
                        }

                        Spacer(modifier = Modifier.width(10.dp))

                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .clickable { onBookmarkClick(recipe.id) }
                                .background(color = AppColors.White),
                            contentAlignment = Alignment.Center,
                        ) {
                            val iconRes =
                                if (recipe.isSaved) R.drawable.bold_bookmark else R.drawable.outline_bookmark
                            val description =
                                if (recipe.isSaved) "saved bookmark" else "unsaved bookmark"
                            Image(
                                painter = painterResource(iconRes),
                                contentDescription = description,
                                colorFilter = ColorFilter.tint(color = AppColors.Primary80),
                                modifier = Modifier.size(16.dp),
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeCardPreview() {
    RecipeCard(recipe = recipe)
}

@Preview(showBackground = true)
@Composable
fun LongNameRecipeCardPreview() {
    val recipe = Recipe(
        id = 1L,
        name = "spice roasted chicken with flavored rice".repeat(3),
        imageUrl = "https://cdn.pixabay.com/photo/2017/11/10/15/04/steak-2936531_1280.jpg",
        chef = "Chef John",
        time = 20,
        rating = 4.0,
    )
    RecipeCard(recipe = recipe)
}

@Preview(showBackground = true)
@Composable
fun SmallSizeRecipeCardPreview() {
    RecipeCard(recipe = recipe, size = RecipeCardSize.Small, modifier = Modifier.width(150.dp))
}

@Preview(showBackground = true)
@Composable
fun LargeSizeRecipeCardPreview() {
    RecipeCard(recipe = recipe, size = RecipeCardSize.Large)
}

private val recipe = Recipe(
    id = 1L,
    name = "spice roasted chicken with flavored rice",
    imageUrl = "https://cdn.pixabay.com/photo/2017/11/10/15/04/steak-2936531_1280.jpg",
    chef = "Chef John",
    time = 20,
    rating = 4.0,
)
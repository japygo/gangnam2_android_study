package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun RecipeCard(
    recipe: Recipe,
    modifier: Modifier = Modifier,
    imageLoader: @Composable (modifier: Modifier) -> Unit = {
        AsyncImage(
            model = recipe.imageUrl,
            contentDescription = "recipe image",
            contentScale = ContentScale.Crop,
            modifier = it.fillMaxSize(),
        )
    },
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(RoundedCornerShape(10.dp)),
    ) {
        imageLoader(Modifier)

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
                        modifier = Modifier.weight(1f, fill = false),
                    ) {
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

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
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

                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(color = AppColors.White),
                            contentAlignment = Alignment.Center,
                        ) {
                            Image(
                                painter = painterResource(R.drawable.outline_bookmark),
                                contentDescription = "bookmark image",
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
    val recipe = Recipe(
        name = "spice roasted chicken with flavored rice",
        imageUrl = "https://cdn.pixabay.com/photo/2017/11/10/15/04/steak-2936531_1280.jpg",
        chef = "Chef John",
        time = 20,
        rating = 4.0,
    )
    RecipeCard(
        recipe = recipe,
        imageLoader = {
            Image(
                painter = ColorPainter(AppColors.Primary100),
                contentDescription = "image",
                contentScale = ContentScale.Crop,
                modifier = it,
            )
        },
    )
}

@Preview(showBackground = true)
@Composable
fun LongNameRecipeCardPreview() {
    val recipe = Recipe(
        name = "spice roasted chicken with flavored rice".repeat(3),
        imageUrl = "https://cdn.pixabay.com/photo/2017/11/10/15/04/steak-2936531_1280.jpg",
        chef = "Chef John",
        time = 20,
        rating = 4.0,
    )
    RecipeCard(
        recipe = recipe,
        imageLoader = {
            Image(
                painter = ColorPainter(AppColors.Primary100),
                contentDescription = "image",
                contentScale = ContentScale.Crop,
                modifier = it,
            )
        },
    )
}
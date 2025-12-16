package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles
import com.survivalcoding.gangnam2kiandroidstudy.util.orPreview

@Composable
fun DishCard(
    recipe: Recipe,
    modifier: Modifier = Modifier,
    onClick: (Long) -> Unit = {},
) {
    Box(
        modifier = modifier
            .size(150.dp, 231.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick(recipe.id) },
    ) {
        Box(
            modifier = Modifier
                .size(150.dp, 176.dp)
                .background(color = AppColors.Gray4, shape = RoundedCornerShape(12.dp))
                .align(Alignment.BottomCenter),
            contentAlignment = Alignment.BottomCenter,
        ) {
            Column(
                modifier = Modifier.padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = recipe.name,
                    style = AppTextStyles.PoppinsSmallBold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center,
                )

                Spacer(modifier = Modifier.size(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom,
                ) {
                    Column {
                        Text(
                            text = "Time",
                            style = AppTextStyles.PoppinsSmallerRegular.copy(color = AppColors.Gray3),
                        )
                        Spacer(modifier = Modifier.size(5.dp))
                        Text(
                            text = "${recipe.time} Mins",
                            style = AppTextStyles.PoppinsSmallerBold,
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

        AsyncImage(
            model = recipe.imageUrl.orPreview,
            contentDescription = "recipe image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(109.dp, 110.dp)
                .clip(RoundedCornerShape(55.dp))
                .align(Alignment.TopCenter),
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, bottom = 2.dp),
            contentAlignment = Alignment.TopEnd,
        ) {
            Row(
                modifier = Modifier
                    .height(23.dp)
                    .background(
                        color = AppColors.Secondary20,
                        shape = RoundedCornerShape(20.dp),
                    )
                    .padding(horizontal = 7.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(R.drawable.bold_star),
                    contentDescription = "rating star",
                    colorFilter = ColorFilter.tint(color = AppColors.Rating),
                    modifier = Modifier.size(10.dp),
                )
                Text(
                    text = recipe.rating.toString(),
                    style = AppTextStyles.PoppinsSmallerRegular,
                )
            }
        }
    }
}

@Preview
@Composable
fun DishCardPreview() {
    val recipe = Recipe(
        id = 1L,
        name = "Vegetable and Chicken",
        imageUrl = "",
        chef = "Laura Goodman",
        time = 20,
        rating = 4.5,
    )
    DishCard(
        recipe = recipe,
    )
}
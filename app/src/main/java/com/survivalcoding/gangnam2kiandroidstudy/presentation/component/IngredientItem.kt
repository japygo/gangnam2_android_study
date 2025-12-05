package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Ingredient
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles
import com.survivalcoding.gangnam2kiandroidstudy.util.orPreview

@Composable
fun IngredientItem(
    ingredient: Ingredient,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(76.dp)
            .background(
                color = AppColors.Gray4.copy(alpha = 0.5f),
                shape = RoundedCornerShape(12.dp),
            ),
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 15.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            AsyncImage(
                model = ingredient.imageUrl.orPreview,
                contentDescription = "image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(52.dp)
                    .clip(RoundedCornerShape(10.dp)),
            )

            Column(
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = ingredient.name,
                    style = AppTextStyles.PoppinsNormalBold.copy(color = AppColors.Black),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Text(
                text = ingredient.amount,
                style = AppTextStyles.PoppinsSmallRegular.copy(color = AppColors.Gray3),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun IngredientItemPreview() {
    val ingredient = Ingredient(
        name = "Tomatos",
        imageUrl = "https://cdn.pixabay.com/photo/2017/10/06/17/17/tomato-2823826_1280.jpg",
        amount = "500g",
    )
    IngredientItem(ingredient = ingredient)
}

@Preview(showBackground = true)
@Composable
fun LongNameIngredientItemPreview() {
    val ingredient = Ingredient(
        name = "Tomatos".repeat(10),
        imageUrl = "https://cdn.pixabay.com/photo/2017/10/06/17/17/tomato-2823826_1280.jpg",
        amount = "500g",
    )
    IngredientItem(ingredient = ingredient)
}
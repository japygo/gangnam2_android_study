package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.data.model.CategoryFilterType
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun RecipeCategorySelector(
    modifier: Modifier = Modifier,
    category: CategoryFilterType = CategoryFilterType.ALL,
    onCategorySelect: (CategoryFilterType) -> Unit = {},
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
    ) {
        items(items = CategoryFilterType.entries) {
            val shape = RoundedCornerShape(10.dp)
            val isSelected = it == category
            val color = if (isSelected) AppColors.White else AppColors.Primary80
            val backgroundColor = if (isSelected) AppColors.Primary100 else AppColors.White

            Box(
                modifier = Modifier
                    .height(31.dp)
                    .clip(shape)
                    .clickable { onCategorySelect(it) }
                    .background(color = backgroundColor, shape = shape)
                    .padding(horizontal = 20.dp, vertical = 7.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = it.label,
                    style = AppTextStyles.PoppinsSmallerBold.copy(color = color),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeCategorySelectorPreview() {
    RecipeCategorySelector()
}
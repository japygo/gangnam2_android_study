package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun Tabs(
    labels: List<String>,
    selectedIndex: Int,
    modifier: Modifier = Modifier,
    onValueChange: (Int) -> Unit,
) {
    val padding = if (labels.size < 3) 15.dp else 7.dp
    val width = if (labels.size < 3) 150.dp else 107.dp
    val shape = RoundedCornerShape(10.dp)

    Box(
        modifier = modifier.size(width = 375.dp, height = 58.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(padding, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            labels.forEachIndexed { index, label ->
                val backgroundColor =
                    if (index == selectedIndex) AppColors.Primary100 else AppColors.White
                val textColor = if (index == selectedIndex) AppColors.White else AppColors.Primary80

                Box(
                    modifier = Modifier
                        .size(width = width, height = 33.dp)
                        .clip(shape)
                        .clickable { onValueChange(index) }
                        .background(color = backgroundColor, shape = shape),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = label,
                        style = AppTextStyles.PoppinsSmallerBold.copy(color = textColor),
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TwoTabsPreview() {
    Tabs(
        labels = listOf("First", "Second"),
        selectedIndex = 0,
        onValueChange = {},
    )
}

@Preview(showBackground = true)
@Composable
fun LongLabelTwoTabsPreview() {
    Tabs(
        labels = listOf("First".repeat(10), "Second".repeat(10)),
        selectedIndex = 1,
        onValueChange = {},
    )
}

@Preview(showBackground = true)
@Composable
fun ThreeTabsPreview() {
    Tabs(
        labels = listOf("First", "Second", "Third"),
        selectedIndex = 1,
        onValueChange = {},
    )
}

@Preview(showBackground = true)
@Composable
fun LongLabelThreeTabsPreview() {
    Tabs(
        labels = listOf("First".repeat(10), "Second".repeat(10), "Third".repeat(10)),
        selectedIndex = 2,
        onValueChange = {},
    )
}
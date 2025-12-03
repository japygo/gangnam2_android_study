package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    Box(
        modifier = modifier
            .size(375.dp, 58.dp)
            .padding(horizontal = if (labels.size < 3) 30.dp else 20.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            labels.forEachIndexed { index, label ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(33.dp)
                        .padding(horizontal = if (labels.size < 3) 15.dp else 7.dp)
                        .background(
                            color = if (index == selectedIndex) AppColors.Primary100 else AppColors.White,
                            shape = RoundedCornerShape(10.dp),
                        )
                        .clickable { onValueChange(index) },
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            text = label,
                            style = AppTextStyles.PoppinsSmallerBold.copy(color = if (index == selectedIndex) AppColors.White else AppColors.Primary80),
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TabsPreview() {
    Tabs(
        labels = listOf("First", "Second"),
        selectedIndex = 0,
        onValueChange = {},
    )
}

@Preview(showBackground = true)
@Composable
fun TabsPreview3() {
    Tabs(
        labels = listOf("First", "Second", "Third"),
        selectedIndex = 1,
        onValueChange = {},
    )
}
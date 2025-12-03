package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun MediumButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val shape = RoundedCornerShape(10.dp)

    Box(
        modifier = modifier
            .size(width = 243.dp, height = 54.dp)
            .clip(shape)
            .clickable(onClick = onClick)
            .background(color = AppColors.Primary100, shape = shape),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 50.dp, vertical = 15.dp),
            horizontalArrangement = Arrangement.spacedBy(9.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = text,
                style = AppTextStyles.PoppinsNormalBold.copy(color = AppColors.White),
                modifier = Modifier.weight(1f, fill = false),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Icon(
                modifier = Modifier.size(20.dp),
                painter = painterResource(R.drawable.outline_arrow_right),
                contentDescription = "오른쪽 화살표",
                tint = AppColors.White,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MediumButtonPreview() {
    MediumButton(text = "Button", onClick = {})
}

@Preview(showBackground = true)
@Composable
fun LongTextMediumButtonPreview() {
    MediumButton(text = "Button".repeat(10), onClick = {})
}
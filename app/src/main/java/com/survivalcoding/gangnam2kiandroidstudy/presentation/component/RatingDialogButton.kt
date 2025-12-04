package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun RatingDialogButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val shape = RoundedCornerShape(10.dp)
    val backgroundColor = if (isPressed || !enabled) AppColors.Gray4 else AppColors.Rating

    Box(
        modifier = modifier
            .size(61.dp, 20.dp)
            .clip(shape)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
            ) {
                if (enabled) {
                    onClick()
                }
            }
            .background(color = backgroundColor, shape = shape),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            style = AppTextStyles.PoppinsSmallerRegular.copy(
                color = AppColors.White,
                fontSize = 8.sp,
            ),
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 4.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RatingDialogButtonPreview() {
    RatingDialogButton(
        text = "Send",
        onClick = {},
    )
}
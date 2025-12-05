package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors

@Composable
fun Checkbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(17.dp)
            .clip(RoundedCornerShape(5.dp))
            .toggleable(
                value = checked,
                onValueChange = onCheckedChange,
            )
            .border(
                border = BorderStroke(width = 1.dp, color = AppColors.Secondary100),
                shape = RoundedCornerShape(5.dp),
            )
            .background(color = AppColors.White),
        contentAlignment = Alignment.Center,
    ) {
        if (checked) {
            Image(
                painter = painterResource(R.drawable.outline_check_24),
                contentDescription = "Checked",
                colorFilter = ColorFilter.tint(AppColors.Secondary100),
                modifier = Modifier.size(17.dp),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CheckboxPreview() {
    Checkbox(false)
}

@Preview(showBackground = true)
@Composable
fun CheckedCheckboxPreview() {
    Checkbox(true)
}
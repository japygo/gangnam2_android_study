package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun RatingDialog(
    title: String,
    actionName: String,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    properties: DialogProperties = DialogProperties(),
    onChange: (Int) -> Unit,
) {
    var index by rememberSaveable { mutableIntStateOf(-1) }
    val isStarSelected = index > -1

    Dialog(
        onDismissRequest = onDismissRequest,
        properties = properties,
    ) {
        Card(
            modifier = modifier.size(170.dp, 91.dp),
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(color = AppColors.White)
                    .padding(horizontal = 15.dp, vertical = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(5.dp),
            ) {
                Text(
                    text = title,
                    style = AppTextStyles.PoppinsSmallerRegular.copy(color = AppColors.Black),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                Row(
                    modifier = Modifier.padding(vertical = 2.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    repeat(5) {
                        val star =
                            if (isStarSelected && it <= index) R.drawable.bold_star else R.drawable.outline_star
                        Image(
                            painter = painterResource(star),
                            contentDescription = "star $it",
                            colorFilter = ColorFilter.tint(color = AppColors.Rating),
                            modifier = Modifier
                                .size(20.dp)
                                .clickable {
                                    index = it
                                },
                        )
                    }
                }

                RatingDialogButton(
                    text = actionName,
                    enabled = isStarSelected,
                ) {
                    onChange(index + 1)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RatingDialogPreview() {
    RatingDialog(
        title = "Rate recipe",
        actionName = "Send",
        onDismissRequest = {},
    ) {}
}

@Preview(showBackground = true)
@Composable
fun LongLabelRatingDialogPreview() {
    RatingDialog(
        title = "Rate recipe".repeat(10),
        actionName = "Send".repeat(10),
        onDismissRequest = {},
    ) {}
}
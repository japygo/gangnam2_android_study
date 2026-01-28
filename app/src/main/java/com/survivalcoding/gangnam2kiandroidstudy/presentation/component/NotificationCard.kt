package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.core.util.toRelativeTime
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Notification
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles
import kotlinx.datetime.LocalDateTime

@Composable
fun NotificationCard(
    notification: Notification,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = AppColors.Gray4, shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 15.dp, vertical = 10.dp),
    ) {
        Column(
            modifier = Modifier.padding(end = 69.dp),
        ) {
            Text(
                text = notification.title,
                style = AppTextStyles.PoppinsSmallerBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = notification.description,
                style = AppTextStyles.PoppinsSmallerRegular.copy(color = AppColors.Gray3),
                modifier = Modifier.padding(vertical = 5.dp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = notification.createdAt.toRelativeTime(),
                style = AppTextStyles.PoppinsSmallerRegular.copy(
                    color = AppColors.Gray3,
                    fontSize = 7.sp,
                ),
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
        ) {
            NotificationReadIcon(
                isRead = notification.isRead,
                isSaved = notification.isSaved,
            )
        }
    }
}

@Composable
fun NotificationReadIcon(
    isRead: Boolean,
    isSaved: Boolean,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
    ) {
        Box(
            modifier = Modifier
                .size(28.dp)
                .background(
                    color = AppColors.Secondary20,
                    shape = RoundedCornerShape(10.dp),
                ),
        ) {
            Icon(
                painter = painterResource(if (isSaved) R.drawable.bold_document_favorite else R.drawable.bold_document_text),
                contentDescription = "document icon",
                tint = AppColors.Secondary100,
                modifier = Modifier
                    .padding(6.dp)
                    .size(16.dp),
            )
        }

        Box(
            modifier = Modifier
                .size(28.dp)
                .padding(1.dp),
            contentAlignment = Alignment.TopEnd,
        ) {
            if (!isRead) {
                Box(
                    modifier = Modifier
                        .size(5.dp)
                        .background(
                            color = AppColors.Secondary100,
                            shape = CircleShape,
                        )
                        .border(
                            color = AppColors.White,
                            width = 0.5.dp,
                            shape = CircleShape,
                        ),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotificationCardPreview() {
    NotificationCard(
        Notification(
            id = 1,
            title = "New Recipe Alert!",
            description = "Lorem Ipsum tempor incididunt ut labore et dolore,in voluptate velit esse cillum",
            createdAt = LocalDateTime(2025, 12, 15, 21, 50, 50),
        ),
    )
}

@Preview(showBackground = true)
@Composable
fun LongNotificationCardPreview() {
    NotificationCard(
        Notification(
            id = 1,
            title = "New Recipe Alert!".repeat(10),
            description = "Lorem Ipsum tempor incididunt ut labore et dolore".repeat(10),
            createdAt = LocalDateTime(2025, 12, 15, 21, 50, 50),
            isRead = true,
            isSaved = true,
        ),
    )
}

@Preview(showBackground = true)
@Composable
private fun NotificationReadIconPreview() {
    NotificationReadIcon(
        isRead = false,
        isSaved = false,
    )
}

@Preview(showBackground = true)
@Composable
private fun SaveNotificationReadIconPreview() {
    NotificationReadIcon(
        isRead = true,
        isSaved = true,
    )
}
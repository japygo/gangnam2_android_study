package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.survivalcoding.gangnam2kiandroidstudy.core.util.orPreview
import com.survivalcoding.gangnam2kiandroidstudy.core.util.toEnglishFormat
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Review
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles
import kotlinx.datetime.LocalDateTime

@Composable
fun ReviewCard(
    review: Review,
    modifier: Modifier = Modifier,
    onLikeClick: (Long) -> Unit = {},
    onDislikeClick: (Long) -> Unit = {},
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(9.dp),
        ) {
            AsyncImage(
                model = review.profileImageUrl.orPreview,
                contentDescription = "profile image",
                modifier = Modifier
                    .size(31.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                Text(
                    text = review.profileName,
                    style = AppTextStyles.PoppinsSmallerBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = review.createdAt.toEnglishFormat(),
                    style = AppTextStyles.PoppinsSmallerRegular.copy(
                        color = AppColors.Gray3,
                        fontSize = 8.sp,
                    ),
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp),
        ) {
            Text(
                text = review.content,
                style = AppTextStyles.PoppinsSmallerRegular.copy(color = AppColors.Gray1),
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
        ) {
            LikeButton(
                count = review.likeCount,
                isActive = review.isLiked,
                isLiked = true,
                onClick = { onLikeClick(review.id) },
            )
            LikeButton(
                count = review.dislikeCount,
                isActive = review.isDisliked,
                isLiked = false,
                onClick = { onDislikeClick(review.id) },
            )
        }
    }
}

@Composable
fun LikeButton(
    count: Int = 0,
    isActive: Boolean = false,
    isLiked: Boolean = true,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    val backgroundColor = if (isActive) AppColors.Primary80 else AppColors.Primary40
    val emoji = if (isLiked) "👍" else "👎"

    Row(
        modifier = modifier
            .height(14.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable(onClick = onClick)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(10.dp),
            )
            .padding(horizontal = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = emoji,
                fontSize = 8.sp,
                lineHeight = 8.sp,
            )
            Text(
                text = count.toString(),
                style = AppTextStyles.PoppinsSmallerRegular,
                color = AppColors.Gray1,
                fontSize = 8.sp,
                lineHeight = 8.sp,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ReviewCardPreview() {
    val review = Review(
        id = 1,
        content = "Lorem Ipsum tempor incididunt ut labore et dolore,inise voluptate velit esse cillum",
        profileId = 1,
        profileName = "Bella Throne",
        profileImageUrl = "",
        createdAt = LocalDateTime(2020, 6, 12, 19, 35),
        likeCount = 9,
        dislikeCount = 2,
        isLiked = true,
        isDisliked = false,
    )
    ReviewCard(review = review)
}

@Preview(showBackground = true)
@Composable
private fun LikeButtonPreview() {
    LikeButton(count = 9, isActive = true)
}

@Preview(showBackground = true)
@Composable
private fun DislikeButtonPreview() {
    LikeButton(count = 2, isLiked = false)
}
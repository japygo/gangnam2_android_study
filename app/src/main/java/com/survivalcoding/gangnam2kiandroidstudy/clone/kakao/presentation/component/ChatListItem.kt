package com.survivalcoding.gangnam2kiandroidstudy.clone.kakao.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.survivalcoding.gangnam2kiandroidstudy.clone.kakao.data.model.ChatItem
import com.survivalcoding.gangnam2kiandroidstudy.core.util.orPreview
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun ChatListItem(
    chatItem: ChatItem,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        AsyncImage(
            model = chatItem.imageUrl.orPreview,
            contentDescription = "profile image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(50.dp)
                .clip(RoundedCornerShape(20.dp)),
        )
        Column(
            modifier = Modifier.weight(1f),
        ) {
            Text(
                text = chatItem.name,
                style = AppTextStyles.PoppinsSmallBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = chatItem.message,
                style = AppTextStyles.PoppinsSmallerRegular.copy(AppColors.Gray2),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }
        Text(
            text = chatItem.time,
            style = AppTextStyles.PoppinsSmallerRegular.copy(AppColors.Gray2),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ChatListItemPreview() {
    val chatItem = ChatItem(
        imageUrl = "https://picsum.photos/200/300",
        name = "채팅방 이름",
        message = "채팅 내용",
        time = "10:00",
        unreadCount = 0,
    )

    ChatListItem(chatItem = chatItem)
}
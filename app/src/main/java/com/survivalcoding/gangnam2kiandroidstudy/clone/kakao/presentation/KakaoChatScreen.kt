@file:OptIn(ExperimentalMaterial3Api::class)

package com.survivalcoding.gangnam2kiandroidstudy.clone.kakao.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.clone.kakao.data.model.Ad
import com.survivalcoding.gangnam2kiandroidstudy.clone.kakao.data.model.ChatItem
import com.survivalcoding.gangnam2kiandroidstudy.clone.kakao.presentation.component.AdItem
import com.survivalcoding.gangnam2kiandroidstudy.clone.kakao.presentation.component.ChatListItem
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun KakaoChatScreen(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "채팅",
                            style = AppTextStyles.PoppinsHeaderBold,
                        )
                        Row(
                            modifier = Modifier.padding(end = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.outline_search),
                                contentDescription = "search",
                            )
                            Icon(
                                painter = painterResource(R.drawable.outline_document_text),
                                contentDescription = "add",
                            )
                            Icon(
                                painter = painterResource(R.drawable.outline_setting),
                                contentDescription = "settings",
                            )
                        }
                    }
                },
            )
        },
    ) {
        Box(
            modifier = modifier
                .padding(it)
                .fillMaxSize(),
        ) {
            val chatItems = List(20) {
                ChatItem(
                    imageUrl = "https://picsum.photos/200/300",
                    name = "채팅방 이름",
                    message = "채팅 내용",
                    time = "10:00",
                    unreadCount = 0,
                )
            }

            LazyColumn(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                item {
                    AdItem(
                        ad = Ad(
                            title = "광고 제목",
                            description = "광고 설명",
                            imageUrl = "",
                            linkUrl = "",
                        ),
                    )
                }
                items(items = chatItems) { item ->
                    ChatListItem(chatItem = item)
                }
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun KakaoChatScreenPreview() {
    KakaoChatScreen()
}
@file:OptIn(ExperimentalMaterial3Api::class)

package com.survivalcoding.gangnam2kiandroidstudy.clone.youtube.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.clone.youtube.data.model.Video
import com.survivalcoding.gangnam2kiandroidstudy.clone.youtube.presentation.component.VideoItem
import com.survivalcoding.gangnam2kiandroidstudy.core.util.orPreview
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun YouTubeHomeScreen(modifier: Modifier = Modifier) {
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
                            text = "YouTube",
                            style = AppTextStyles.PoppinsHeaderBold,
                        )
                        Row(
                            modifier = Modifier.padding(end = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.outline_document_favorite),
                                contentDescription = "favorite",
                            )
                            Icon(
                                painter = painterResource(R.drawable.outline_notification_bing),
                                contentDescription = "notification",
                            )
                            Icon(
                                painter = painterResource(R.drawable.outline_search),
                                contentDescription = "search",
                            )
                            AsyncImage(
                                model = "".orPreview,
                                contentDescription = "profile image",
                                modifier = Modifier
                                    .size(24.dp)
                                    .clip(RoundedCornerShape(12.dp)),
                                contentScale = ContentScale.Crop,
                            )
                        }
                    }
                },
            )
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Icon(
                    imageVector = Icons.Outlined.Home,
                    contentDescription = "home",
                    modifier = Modifier
                        .size(36.dp)
                        .weight(1f),
                )
                Icon(
                    imageVector = Icons.Outlined.PlayArrow,
                    contentDescription = "shorts",
                    modifier = Modifier
                        .size(36.dp)
                        .weight(1f),
                )
                Icon(
                    imageVector = Icons.Outlined.AddCircle,
                    contentDescription = "add",
                    modifier = Modifier
                        .size(36.dp)
                        .weight(1f),
                )
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = "person",
                    modifier = Modifier
                        .size(36.dp)
                        .weight(1f),
                )
                Icon(
                    imageVector = Icons.Outlined.Menu,
                    contentDescription = "menu",
                    modifier = Modifier
                        .size(36.dp)
                        .weight(1f),
                )
            }
        },
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(it),
        ) {
            LazyRow(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .height(50.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                val menus = listOf("전체", "게임", "뉴스", "실시간", "믹스", "음악")

                items(items = menus) { menu ->
                    Box(
                        modifier = Modifier
                            .background(
                                color = AppColors.Black,
                                shape = RoundedCornerShape(8.dp),
                            )
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                    ) {
                        Text(
                            text = menu,
                            style = AppTextStyles.PoppinsSmallRegular.copy(AppColors.White),
                        )
                    }
                }
            }
            LazyColumn(
                modifier = Modifier.padding(top = 50.dp),
            ) {
                val videos = List(10) { id ->
                    Video(
                        id = id.toLong(),
                        title = "스파6 - 세번 잡히면 죽습니다.",
                        thumbnailUrl = "thumbnailUrl",
                        channelName = "아빠킹",
                        channelImageUrl = "channelImageUrl",
                        viewCount = 40000L,
                        publishedAt = "2025-12-08 12:22:22",
                        duration = "16:21",
                    )
                }

                items(items = videos) { video ->
                    VideoItem(video = video)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun YoutubeHomeScreenPreview() {
    YouTubeHomeScreen()
}
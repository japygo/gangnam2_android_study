package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.core.util.isToday
import com.survivalcoding.gangnam2kiandroidstudy.core.util.isYesterday
import com.survivalcoding.gangnam2kiandroidstudy.data.repository.MockNotificationRepositoryImpl
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.NotificationFilterType
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.NotificationCard
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.Tabs
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun NotificationScreen(
    modifier: Modifier = Modifier,
    uiState: NotificationUiState = NotificationUiState(),
    onTabClick: (Int) -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = AppColors.White)
            .statusBarsPadding()
            .navigationBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Notifications",
            style = AppTextStyles.PoppinsMediumBold,
            modifier = Modifier.padding(vertical = 10.dp),
        )

        Tabs(
            labels = NotificationFilterType.entries.map { it.label },
            selectedIndex = uiState.selectedTabIndex,
            onValueChange = onTabClick,
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp)
                .padding(horizontal = 30.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (!uiState.todayNotifications.isEmpty()) {
                item {
                    Text(
                        text = "Today",
                        style = AppTextStyles.PoppinsSmallerBold,
                    )
                }

                items(
                    items = uiState.todayNotifications,
                    key = { it.id },
                ) {
                    NotificationCard(notification = it)
                }
            }

            if (!uiState.yesterdayNotifications.isEmpty()) {
                item {
                    Text(
                        text = "Yesterday",
                        style = AppTextStyles.PoppinsSmallerBold,
                    )
                }

                items(
                    items = uiState.yesterdayNotifications,
                    key = { it.id },
                ) {
                    NotificationCard(notification = it)
                }
            }

            if (!uiState.groupedOtherNotifications.isEmpty()) {
                uiState.groupedOtherNotifications.forEach { (date, notifications) ->
                    item {
                        Text(
                            text = date.toString(),
                            style = AppTextStyles.PoppinsSmallerBold,
                        )
                    }

                    items(
                        items = notifications,
                        key = { it.id },
                    ) {
                        NotificationCard(notification = it)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NotificationScreenPreview() {
    val notifications = MockNotificationRepositoryImpl.mockNotifications
    val (todayNotifications, notTodayNotifications) = notifications.partition { it.createdAt.isToday() }
    val (yesterdayNotifications, otherNotifications) = notTodayNotifications.partition { it.createdAt.isYesterday() }
    val groupedOtherNotifications = otherNotifications
        .groupBy { it.createdAt.date }
        .toSortedMap(compareByDescending { it })

    NotificationScreen(
        uiState = NotificationUiState(
            todayNotifications = todayNotifications,
            yesterdayNotifications = yesterdayNotifications,
            groupedOtherNotifications = groupedOtherNotifications,
        ),
    )
}
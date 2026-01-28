package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.notification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.gangnam2kiandroidstudy.core.AppResult
import com.survivalcoding.gangnam2kiandroidstudy.core.util.isToday
import com.survivalcoding.gangnam2kiandroidstudy.core.util.isYesterday
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.NotificationFilterType
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.NotificationSearchCondition
import com.survivalcoding.gangnam2kiandroidstudy.domain.usecase.GetNotificationsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NotificationViewModel(
    private val getNotificationsUseCase: GetNotificationsUseCase,
) : ViewModel() {
    private val _uiState: MutableStateFlow<NotificationUiState> =
        MutableStateFlow(NotificationUiState())
    val uiState: StateFlow<NotificationUiState> = _uiState.asStateFlow()

    init {
        fetchNotifications()
    }

    fun fetchNotifications(
        filterType: NotificationFilterType = NotificationFilterType.ALL,
    ) {
        setLoading(true)

        viewModelScope.launch {
            try {
                val searchCondition = NotificationSearchCondition(filterType)

                when (val result = getNotificationsUseCase(searchCondition)) {
                    is AppResult.Success -> {
                        val notifications = result.data
                        val (todayNotifications, notTodayNotifications) = notifications.partition { it.createdAt.isToday() }
                        val (yesterdayNotifications, otherNotifications) = notTodayNotifications.partition { it.createdAt.isYesterday() }
                        val groupedOtherNotifications = otherNotifications
                            .groupBy { it.createdAt.date }
                            .toSortedMap(compareByDescending { it })

                        _uiState.update {
                            it.copy(
                                todayNotifications = todayNotifications,
                                yesterdayNotifications = yesterdayNotifications,
                                groupedOtherNotifications = groupedOtherNotifications,
                            )
                        }
                    }
                    is AppResult.Error -> {
                        _uiState.update {
                            it.copy(
                                todayNotifications = emptyList(),
                                yesterdayNotifications = emptyList(),
                                groupedOtherNotifications = emptyMap(),
                            )
                        }
                    }
                }
            } finally {
                setLoading(false)
            }
        }
    }

    fun changeTab(selectedIndex: Int) {
        _uiState.update { it.copy(selectedTabIndex = selectedIndex) }
        fetchNotifications(NotificationFilterType.entries[selectedIndex])
    }

    private fun setLoading(isLoading: Boolean) {
        _uiState.update { it.copy(isLoading = isLoading) }
    }
}
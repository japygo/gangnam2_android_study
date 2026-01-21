package com.survivalcoding.gangnam2kiandroidstudy.legacy.p02state

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class StateLegacyViewModel(
    val savedStateHandle: SavedStateHandle, // 강제 종료 대응하려면
) : ViewModel() {
    var count = 0

    init {
        count = savedStateHandle["count"] ?: 0 // 복원
    }

    fun increment() {
        count++
        savedStateHandle["count"] = count // 저장
    }
}
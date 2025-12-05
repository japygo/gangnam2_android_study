package com.survivalcoding.gangnam2kiandroidstudy.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalInspectionMode
import com.survivalcoding.gangnam2kiandroidstudy.R

val String.orPreview: Any
    @Composable get() = if (LocalInspectionMode.current) R.drawable.preview_placeholder else this

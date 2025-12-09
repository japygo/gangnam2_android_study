package com.survivalcoding.gangnam2kiandroidstudy.core

data class Response<T>(
    val statusCode: Int,
    val headers: Map<String, List<String>>,
    val body: T? = null,
)
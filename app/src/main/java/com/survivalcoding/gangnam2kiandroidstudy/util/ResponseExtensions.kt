package com.survivalcoding.gangnam2kiandroidstudy.util

import com.survivalcoding.gangnam2kiandroidstudy.core.Response
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.util.toMap
import kotlinx.serialization.json.Json

fun <T> Response<T>.isSuccess(): Boolean = this.statusCode in (200 until 300)
fun <T> Response<T>.isClientError(): Boolean = this.statusCode in (400 until 500)
fun <T> Response<T>.isServerError(): Boolean = this.statusCode in (500 until 600)
fun <T> Response<T>.isFail(): Boolean = this.isClientError() || this.isServerError()

const val EMPTY_JSON_VALUE = "{}"

val json = Json {
    isLenient = true
    ignoreUnknownKeys = true
    coerceInputValues = true
}

suspend inline fun <reified T> HttpResponse.toResponse(): Response<T> {
    val statusCode = this.status.value
    val headerMap = this.headers.toMap()
    val jsonString = this.body<String>().trim()

    if (jsonString.isEmpty() || jsonString == EMPTY_JSON_VALUE) {
        return Response(statusCode, headerMap)
    }

    val body = json.decodeFromString<T>(jsonString)

    return Response(
        statusCode,
        headerMap,
        body,
    )
}

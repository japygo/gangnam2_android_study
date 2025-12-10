package com.survivalcoding.gangnam2kiandroidstudy.data.datasource

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class RemoteRecipeDataSourceImplTest {

    private val mockEngine = MockEngine { request ->
        when (request.url.toString()) {
            RemoteRecipeDataSourceImpl.BASE_URL -> {
                respond(
                    MockRecipeDataSourceImpl().jsonString,
                    HttpStatusCode.OK,
                    headersOf(HttpHeaders.ContentType, "application/json"),
                )
            }

            else -> {
                respond(
                    "",
                    HttpStatusCode.NotFound,
                    headersOf(HttpHeaders.ContentType, "application/json"),
                )
            }
        }
    }
    private val httpClient = HttpClient(mockEngine)
    private val dataSource = RemoteRecipeDataSourceImpl(httpClient)

    @Test
    fun `저장된 레시피 목록을 가져온다`() = runTest {
        val response = dataSource.getSavedRecipes()

        assertEquals(HttpStatusCode.OK.value, response.statusCode)
        assertEquals(10, response.body?.recipes?.size)
    }

    @Test
    fun `검색된 레시피 목록을 가져온다`() = runTest {
        val searchText = "rice"
        val response = dataSource.getRecipes(searchText)

        assertEquals(HttpStatusCode.OK.value, response.statusCode)
        assertEquals(2, response.body?.recipes?.size)
    }
}
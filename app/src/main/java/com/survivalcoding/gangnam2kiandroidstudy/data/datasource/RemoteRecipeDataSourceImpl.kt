package com.survivalcoding.gangnam2kiandroidstudy.data.datasource

import com.survivalcoding.gangnam2kiandroidstudy.core.HttpClientFactory
import com.survivalcoding.gangnam2kiandroidstudy.core.Response
import com.survivalcoding.gangnam2kiandroidstudy.data.dto.RecipesDto
import com.survivalcoding.gangnam2kiandroidstudy.util.toResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class RemoteRecipeDataSourceImpl(
    private val client: HttpClient = HttpClientFactory.create(),
    private val baseUrl: String = BASE_URL,
) : RecipeDataSource {

    override suspend fun getSavedRecipes(): Response<RecipesDto> {
        val httpResponse = client.get(baseUrl)

        return httpResponse.toResponse()
    }

    companion object {
        const val BASE_URL =
            "https://raw.githubusercontent.com/junsuk5/mock_json/refs/heads/main/recipe/recipes.json"
    }
}
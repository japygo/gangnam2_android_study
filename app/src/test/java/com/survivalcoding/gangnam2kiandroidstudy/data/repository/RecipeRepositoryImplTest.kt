package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.survivalcoding.gangnam2kiandroidstudy.core.AppResult
import com.survivalcoding.gangnam2kiandroidstudy.core.Response
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.dto.RecipeDto
import com.survivalcoding.gangnam2kiandroidstudy.data.dto.RecipesDto
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.RecipeSearchCondition
import io.mockk.MockKAnnotations
import io.mockk.bdd.coGiven
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class RecipeRepositoryImplTest {

    @MockK
    private lateinit var dataSource: RecipeDataSource

    @InjectMockKs
    private lateinit var repository: RecipeRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `저장된 레시피 목록을 가져온다`() = runTest {
        coGiven { dataSource.getSavedRecipes() } returns
                Response(
                    statusCode = 200,
                    headers = emptyMap(),
                    body = RecipesDto(
                        recipes = listOf(
                            RecipeDto(name = "test1"),
                            RecipeDto(name = "test2"),
                            RecipeDto(name = "test3"),
                        ),
                    ),
                )

        val result = repository.getSavedRecipes()

        assertTrue(result is AppResult.Success)

        result as AppResult.Success

        assertEquals(3, result.data.size)
        assertEquals("test1", result.data[0].name)
        assertEquals("test2", result.data[1].name)
        assertEquals("test3", result.data[2].name)
    }

    @Test
    fun `검색된 레시피 목록을 가져온다`() = runTest {
        coGiven { dataSource.getRecipes(any()) } returns
                Response(
                    statusCode = 200,
                    headers = emptyMap(),
                    body = RecipesDto(
                        recipes = listOf(
                            RecipeDto(name = "test1"),
                            RecipeDto(name = "test2"),
                            RecipeDto(name = "test3"),
                        ),
                    ),
                )

        val searchText = "test"
        val searchCondition = RecipeSearchCondition(searchText)
        val result = repository.getRecipes(searchCondition)

        assertTrue(result is AppResult.Success)

        result as AppResult.Success

        assertEquals(3, result.data.size)
        assertEquals("test1", result.data[0].name)
        assertEquals("test2", result.data[1].name)
        assertEquals("test3", result.data[2].name)
    }
}
package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import android.util.Log
import com.survivalcoding.gangnam2kiandroidstudy.core.AppResult
import com.survivalcoding.gangnam2kiandroidstudy.core.NetworkError
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.mapper.toModel
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.data.model.RecipeSearchCondition
import com.survivalcoding.gangnam2kiandroidstudy.util.isFail
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import java.io.IOException

class RecipeRepositoryImpl(
    private val dataSource: RecipeDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : RecipeRepository {

    override suspend fun getSavedRecipes(): AppResult<List<Recipe>, NetworkError> =
        withContext(dispatcher) {
            handleNetworkError {
                val response = dataSource.getSavedRecipes()

                if (response.isFail()) {
                    return@handleNetworkError AppResult.Error(NetworkError.HttpError(response.statusCode))
                }

                val recipes = response.body?.toModel()
                    ?: return@handleNetworkError AppResult.Error(NetworkError.Unknown("응답 데이터가 비어있습니다"))

                AppResult.Success(recipes)
            }
        }

    override suspend fun getRecipes(searchCondition: RecipeSearchCondition): AppResult<List<Recipe>, NetworkError> =
        withContext(dispatcher) {
            handleNetworkError {
                val response = dataSource.getRecipes(searchCondition)

                if (response.isFail()) {
                    return@handleNetworkError AppResult.Error(NetworkError.HttpError(response.statusCode))
                }

                val recipes = response.body?.toModel()
                    ?: return@handleNetworkError AppResult.Error(NetworkError.Unknown("응답 데이터가 비어있습니다"))

                AppResult.Success(recipes)
            }
        }

    private suspend fun <T> handleNetworkError(block: suspend () -> AppResult<T, NetworkError>): AppResult<T, NetworkError> =
        try {
            block()
        } catch (e: IOException) {
            Log.e("RecipeRepositoryImpl", e.stackTraceToString())
            AppResult.Error(NetworkError.NetworkUnavailable)
        } catch (e: TimeoutCancellationException) {
            Log.e("RecipeRepositoryImpl", e.stackTraceToString())
            AppResult.Error(NetworkError.Timeout)
        } catch (e: SerializationException) {
            Log.e("RecipeRepositoryImpl", e.stackTraceToString())
            AppResult.Error(NetworkError.ParseError)
        } catch (e: Exception) {
            Log.e("RecipeRepositoryImpl", e.stackTraceToString())
            AppResult.Error(NetworkError.Unknown("에러가 발생했습니다"))
        }
}
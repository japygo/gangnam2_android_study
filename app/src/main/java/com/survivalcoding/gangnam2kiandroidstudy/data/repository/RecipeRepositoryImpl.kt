package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import android.util.Log
import com.survivalcoding.gangnam2kiandroidstudy.core.NetworkError
import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.mapper.toModel
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe
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

    override suspend fun getSavedRecipes(): Result<List<Recipe>, NetworkError> =
        withContext(dispatcher) {
            handleNetworkError {
                val response = dataSource.getSavedRecipes()

                if (response.isFail()) {
                    return@handleNetworkError Result.Error(NetworkError.HttpError(response.statusCode))
                }

                val recipes = response.body?.toModel()
                    ?: return@handleNetworkError Result.Error(NetworkError.Unknown("응답 데이터가 비어있습니다"))

                Result.Success(recipes)
            }
        }

    private suspend fun <T> handleNetworkError(block: suspend () -> Result<T, NetworkError>): Result<T, NetworkError> =
        try {
            block()
        } catch (e: IOException) {
            Log.e("RecipeRepositoryImpl", e.stackTraceToString())
            Result.Error(NetworkError.NetworkUnavailable)
        } catch (e: TimeoutCancellationException) {
            Log.e("RecipeRepositoryImpl", e.stackTraceToString())
            Result.Error(NetworkError.Timeout)
        } catch (e: SerializationException) {
            Log.e("RecipeRepositoryImpl", e.stackTraceToString())
            Result.Error(NetworkError.ParseError)
        } catch (e: Exception) {
            Log.e("RecipeRepositoryImpl", e.stackTraceToString())
            Result.Error(NetworkError.Unknown("에러가 발생했습니다"))
        }
}
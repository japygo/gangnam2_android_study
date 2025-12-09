package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import android.util.Log
import com.survivalcoding.gangnam2kiandroidstudy.core.NetworkError
import com.survivalcoding.gangnam2kiandroidstudy.core.Result
import com.survivalcoding.gangnam2kiandroidstudy.data.datasource.RecipeDataSource
import com.survivalcoding.gangnam2kiandroidstudy.data.mapper.toModel
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Recipe
import com.survivalcoding.gangnam2kiandroidstudy.util.isFail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import java.io.IOException

class RecipeRepositoryImpl(
    private val dataSource: RecipeDataSource,
) : RecipeRepository {

    override suspend fun getSavedRecipes(): Result<List<Recipe>, NetworkError> =
        withContext(Dispatchers.IO) {
            handleNetworkError {
                val response = dataSource.getSavedRecipes()

                if (response.isFail()) {
                    return@handleNetworkError Result.Error(NetworkError.HttpError(response.statusCode))
                }

                val recipes = response.body?.toModel()
                    ?: return@handleNetworkError Result.Error(NetworkError.Unknown("잘못된 사용자입니다"))

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
package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.google.firebase.Firebase
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.survivalcoding.gangnam2kiandroidstudy.core.AppResult
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.SignUpRepository
import kotlinx.coroutines.tasks.await

class FirebaseSignUpRepositoryImpl : SignUpRepository {

    private val auth = Firebase.auth

    override suspend fun signUpWithEmail(email: String, password: String): AppResult<Unit, String> {
        return try {
            auth.createUserWithEmailAndPassword(email, password).await()

            AppResult.Success(Unit)
        } catch (e: Exception) {
            e.printStackTrace()
            AppResult.Error(e.message ?: "Unknown error")
        }
    }

    override suspend fun signUpWithGoogle(idToken: String): AppResult<Unit, String> {
        return try {
            val credential = GoogleAuthProvider.getCredential(idToken, null)

            auth.signInWithCredential(credential).await()

            AppResult.Success(Unit)
        } catch (e: Exception) {
            e.printStackTrace()
            AppResult.Error(e.message ?: "Unknown error")
        }
    }
}
package com.survivalcoding.gangnam2kiandroidstudy.data.repository

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.survivalcoding.gangnam2kiandroidstudy.core.HttpException
import com.survivalcoding.gangnam2kiandroidstudy.domain.repository.BookmarkRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class FirebaseBookmarkRepositoryImpl : BookmarkRepository {

    private val auth = Firebase.auth
    private val firestore = Firebase.firestore

    override suspend fun toggleBookmark(recipeId: Long) {
        val user = auth.currentUser ?: throw HttpException(401)

        val bookmarkReference = firestore.collection("users")
            .document(user.uid)
            .collection("bookmarks")
            .document(recipeId.toString())

        firestore.runTransaction { transaction ->
            val bookmark = transaction.get(bookmarkReference)
            if (bookmark.exists()) {
                transaction.delete(bookmarkReference)
            } else {
                transaction.set(bookmarkReference, mapOf("recipeId" to recipeId))
            }
        }.await()
    }

    override fun getBookmarks(profileId: Long): Flow<List<Long>> {
        return callbackFlow {
            val user = auth.currentUser ?: throw HttpException(401)

            val subscription = firestore.collection("users")
                .document(user.uid)
                .collection("bookmarks")
                .addSnapshotListener { snapshot, error ->
                    if (error != null) {
                        close(error)
                        return@addSnapshotListener
                    }

                    if (snapshot != null) {
                        val bookmarkIds = snapshot.documents.mapNotNull { it.id.toLongOrNull() }
                        trySend(bookmarkIds)
                    }
                }

            awaitClose { subscription.remove() }
        }
    }
}
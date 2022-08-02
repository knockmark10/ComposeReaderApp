package com.markoid.readerapp.data.remote

import android.util.Log
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.markoid.readerapp.domain.entities.UserProfile
import com.markoid.readerapp.presentation.enums.FirestoreCollections.USERS
import com.markoid.readerapp.presentation.extensions.getCollection
import com.markoid.readerapp.presentation.extensions.toUserProfile
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class AuthRemoteDatabaseImpl
@Inject constructor(
  private val remoteDatabase: FirebaseFirestore
) : AuthRemoteDatabase {

  override fun saveUser(user: FirebaseUser) {
    val profile = UserProfile(
      id = user.uid,
      displayName = user.email?.split("@")?.firstOrNull().orEmpty(),
      email = user.email.orEmpty()
    )
    remoteDatabase.getCollection(USERS).add(profile.convertToMap())
  }

  override suspend fun getUserById(
    userId: String
  ): UserProfile = suspendCancellableCoroutine { continuation ->
    remoteDatabase.getCollection(USERS)
      .get()
      .addOnSuccessListener { querySnapshot ->
        if (querySnapshot.isEmpty) {
          continuation.resumeWithException(IllegalArgumentException("There is no such user"))
        } else {
          // Get user from list with the id
          val user = querySnapshot.documents
            .toUserProfile()
            .firstOrNull { it.id == userId }
          // Check if user exists, otherwise throw exception
          if (user == null) continuation.resumeWithException(IllegalArgumentException("There is no user with id $userId"))
          else continuation.resume(user)
        }
      }
      .addOnFailureListener {
        Log.e("AuthRemoteDatabaseImpl", "getUserById with id $userId was not valid", it)
        continuation.resumeWithException(it)
      }
  }
}
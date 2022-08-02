package com.markoid.readerapp.data.remote

import android.util.Log
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.markoid.readerapp.data.entities.UserFormEntry
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class AuthServiceImpl
@Inject constructor(
  private val authenticator: FirebaseAuth
) : AuthService {

  override suspend fun getCurrentUser(): FirebaseUser? =
    authenticator.currentUser

  override suspend fun isUserAuthenticated(): Boolean =
    authenticator.currentUser != null

  override suspend fun signIn(
    user: UserFormEntry
  ): AuthResult = suspendCancellableCoroutine { continuation ->
    authenticator.signInWithEmailAndPassword(user.email, user.password)
      .addOnCompleteListener {
        if (it.isSuccessful) {
          Log.d("AuthServiceImpl", "Successful signIn ${user.email} ${user.password}")
          continuation.resume(it.result)
        } else {
          Log.e("AuthServiceImpl", "Failed signIn", it.exception)
          continuation.resumeWithException(
            it.exception ?: IllegalStateException("There was a problem with signing in")
          )
        }
      }
      .addOnFailureListener {
        Log.e("AuthServiceImpl", "Failed signIn", it)
        if (continuation.isActive) {
          continuation.resumeWithException(it)
        }
      }
  }

  override suspend fun signUp(
    user: UserFormEntry
  ): AuthResult = suspendCancellableCoroutine { continuation ->
    authenticator.createUserWithEmailAndPassword(user.email, user.password)
      .addOnCompleteListener {
        if (it.isSuccessful) {
          Log.d("AuthServiceImpl", "Successful signUp ${user.email} ${user.password}")
          continuation.resume(it.result)
        } else {
          Log.d("AuthServiceImpl", "Failed signUp", it.exception)
          continuation.resumeWithException(
            it.exception ?: IllegalStateException("There was a problem with signing in")
          )
        }
      }
      .addOnFailureListener {
        Log.d("AuthServiceImpl", "Failed signUp", it)
        if (continuation.isActive) {
          continuation.resumeWithException(it)
        }
      }
  }

  override suspend fun signOut() {
    authenticator.signOut()
  }
}
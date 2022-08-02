package com.markoid.readerapp.data.datasources

import com.google.firebase.auth.FirebaseUser
import com.markoid.readerapp.data.entities.UserFormEntry
import com.markoid.readerapp.data.remote.AuthRemoteDatabase
import com.markoid.readerapp.data.remote.AuthService
import com.markoid.readerapp.domain.entities.UserProfile
import kotlinx.coroutines.delay
import javax.inject.Inject

class AuthDataSourceImpl
@Inject constructor(
  private val authService: AuthService,
  private val authRemoteDatabase: AuthRemoteDatabase
) : AuthDataSource {

  override suspend fun isUserAuthenticated(): Boolean = authService.isUserAuthenticated()

  override suspend fun getUserById(userId: String): UserProfile =
    authRemoteDatabase.getUserById(userId)

  override suspend fun getCurrentUser(): FirebaseUser? =
    authService.getCurrentUser()

  override suspend fun signIn(userFormEntry: UserFormEntry): FirebaseUser {
    delay(2_000L)
    val result = authService.signIn(userFormEntry)
    return result.user ?: throw NullPointerException("User is null")
  }

  override suspend fun signUp(userFormEntry: UserFormEntry): FirebaseUser {
    delay(1_000L)
    val result = authService.signUp(userFormEntry)
    return result.user ?: throw NullPointerException("User is null")
  }

  override suspend fun saveUser(user: FirebaseUser) {
    authRemoteDatabase.saveUser(user)
  }

  override suspend fun signOut() {
    authService.signOut()
  }
}
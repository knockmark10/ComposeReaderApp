package com.markoid.readerapp.domain.repositories

import com.markoid.readerapp.data.datasources.AuthDataSource
import com.markoid.readerapp.data.entities.UserCredentials
import com.markoid.readerapp.data.entities.UserFormEntry
import com.markoid.readerapp.domain.entities.UserProfile
import javax.inject.Inject

class AuthRepositoryImpl
@Inject constructor(
  private val authDataSource: AuthDataSource
) : AuthRepository {

  override suspend fun getCurrentUser(): UserProfile {
    // Get current user from authenticator
    val currentUser = authDataSource.getCurrentUser()
      ?: throw IllegalStateException("There is not current user available.")
    // Return user stored in database by using his id
    return authDataSource.getUserById(currentUser.uid)
  }

  override suspend fun isUserAuthenticated(): Boolean = authDataSource.isUserAuthenticated()

  override suspend fun signIn(userFormEntry: UserFormEntry): UserCredentials {
    val credential = authDataSource.signIn(userFormEntry)
    return UserCredentials(
      name = credential.displayName.orEmpty(),
      email = userFormEntry.email,
      password = userFormEntry.password
    )
  }

  override suspend fun signUp(userFormEntry: UserFormEntry): UserCredentials {
    // Create account in Firebase Authentication
    val credential = authDataSource.signUp(userFormEntry)
    // Use credential to create user on remote database
    authDataSource.saveUser(credential)
    // Return custom user credential
    return UserCredentials(
      name = credential.displayName.orEmpty(),
      email = userFormEntry.email,
      password = userFormEntry.password
    )
  }

  override suspend fun signOut() {
    authDataSource.signOut()
  }
}
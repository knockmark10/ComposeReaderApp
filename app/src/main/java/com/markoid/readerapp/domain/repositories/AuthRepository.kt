package com.markoid.readerapp.domain.repositories

import com.markoid.readerapp.data.entities.UserCredentials
import com.markoid.readerapp.data.entities.UserFormEntry
import com.markoid.readerapp.domain.entities.UserProfile

interface AuthRepository {
  suspend fun getCurrentUser(): UserProfile
  suspend fun isUserAuthenticated(): Boolean
  suspend fun signIn(userFormEntry: UserFormEntry): UserCredentials
  suspend fun signUp(userFormEntry: UserFormEntry): UserCredentials
  suspend fun signOut()
}
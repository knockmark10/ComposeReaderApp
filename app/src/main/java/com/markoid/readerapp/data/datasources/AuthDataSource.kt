package com.markoid.readerapp.data.datasources

import com.google.firebase.auth.FirebaseUser
import com.markoid.readerapp.data.entities.UserFormEntry
import com.markoid.readerapp.domain.entities.UserProfile

interface AuthDataSource {
  suspend fun isUserAuthenticated(): Boolean
  suspend fun getUserById(userId: String): UserProfile
  suspend fun getCurrentUser(): FirebaseUser?
  suspend fun signIn(userFormEntry: UserFormEntry): FirebaseUser
  suspend fun signUp(userFormEntry: UserFormEntry): FirebaseUser
  suspend fun saveUser(user: FirebaseUser)
  suspend fun signOut()
}
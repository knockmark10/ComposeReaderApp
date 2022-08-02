package com.markoid.readerapp.data.remote

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.markoid.readerapp.data.entities.UserFormEntry

interface AuthService {
  suspend fun getCurrentUser(): FirebaseUser?
  suspend fun isUserAuthenticated(): Boolean
  suspend fun signIn(user: UserFormEntry): AuthResult
  suspend fun signUp(user: UserFormEntry): AuthResult
  suspend fun signOut()
}
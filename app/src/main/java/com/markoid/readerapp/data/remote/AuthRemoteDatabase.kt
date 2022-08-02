package com.markoid.readerapp.data.remote

import com.google.firebase.auth.FirebaseUser
import com.markoid.readerapp.domain.entities.UserProfile

interface AuthRemoteDatabase {
  fun saveUser(user: FirebaseUser)
  suspend fun getUserById(userId: String): UserProfile
}
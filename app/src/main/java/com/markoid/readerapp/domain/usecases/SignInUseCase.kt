package com.markoid.readerapp.domain.usecases

import com.markoid.readerapp.data.entities.UserCredentials
import com.markoid.readerapp.data.entities.UserFormEntry
import com.markoid.readerapp.domain.repositories.AuthRepository
import javax.inject.Inject

class SignInUseCase
@Inject constructor(
  private val authRepository: AuthRepository
) : UseCase<UserCredentials, UserFormEntry>() {

  override suspend fun onExecute(
    request: UserFormEntry
  ): UserCredentials = authRepository.signIn(request)
}
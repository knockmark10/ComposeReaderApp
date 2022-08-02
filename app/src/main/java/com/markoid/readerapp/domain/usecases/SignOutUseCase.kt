package com.markoid.readerapp.domain.usecases

import com.markoid.readerapp.domain.repositories.AuthRepository
import javax.inject.Inject

class SignOutUseCase
@Inject constructor(
  private val authRepository: AuthRepository
) {

  suspend operator fun invoke() {
    authRepository.signOut()
  }
}
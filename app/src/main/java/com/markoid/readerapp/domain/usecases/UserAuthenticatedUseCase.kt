package com.markoid.readerapp.domain.usecases

import com.markoid.readerapp.domain.repositories.AuthRepository
import javax.inject.Inject

class UserAuthenticatedUseCase
@Inject constructor(
  private val authRepository: AuthRepository
) {

  suspend operator fun invoke(): Boolean = authRepository.isUserAuthenticated()
}
package com.markoid.readerapp.domain.usecases

import com.markoid.readerapp.domain.entities.UserProfile
import com.markoid.readerapp.domain.repositories.AuthRepository
import javax.inject.Inject

class GetUserProfileUseCase
@Inject constructor(
  private val authRepository: AuthRepository
) : UseCase<UserProfile, Unit>() {

  override suspend fun onExecute(request: Unit): UserProfile = authRepository.getCurrentUser()
}
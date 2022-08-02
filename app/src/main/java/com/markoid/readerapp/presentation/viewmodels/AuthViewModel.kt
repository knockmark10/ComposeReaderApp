package com.markoid.readerapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.markoid.readerapp.data.entities.UserFormEntry
import com.markoid.readerapp.domain.usecases.GetUserProfileUseCase
import com.markoid.readerapp.domain.usecases.SignInUseCase
import com.markoid.readerapp.domain.usecases.SignOutUseCase
import com.markoid.readerapp.domain.usecases.SignUpUseCase
import com.markoid.readerapp.domain.usecases.UserAuthenticatedUseCase
import com.markoid.readerapp.presentation.dispatchers.DispatcherProvider
import com.markoid.readerapp.presentation.observers.UseCaseObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel
@Inject constructor(
  private val dispatcherProvider: DispatcherProvider,
  private val isUserAuthenticatedUseCase: UserAuthenticatedUseCase,
  getUserProfileUseCase: GetUserProfileUseCase,
  signInUseCase: SignInUseCase,
  signUpUseCase: SignUpUseCase,
  private val signOutUseCase: SignOutUseCase
) : ViewModel() {

  val signInObserver = UseCaseObserver(signInUseCase, dispatcherProvider, viewModelScope)

  val signUpObserver = UseCaseObserver(signUpUseCase, dispatcherProvider, viewModelScope)

  val userProfileObserver =
    UseCaseObserver(getUserProfileUseCase, dispatcherProvider, viewModelScope)

  private val _userAuthenticated: MutableStateFlow<Boolean?> = MutableStateFlow(null)
  val userAuthenticated = _userAuthenticated.asStateFlow()

  fun signIn(user: UserFormEntry) {
    signInObserver.execute(user)
  }

  fun signUp(user: UserFormEntry) {
    signUpObserver.execute(user)
  }

  fun signOut() = viewModelScope.launch(dispatcherProvider.io) {
    signOutUseCase()
  }

  fun getUserAuthenticatedFlow() = viewModelScope.launch(dispatcherProvider.io) {
    _userAuthenticated.emit(isUserAuthenticatedUseCase.invoke())
  }

  fun getCurrentUser() {
    userProfileObserver.execute(Unit)
  }
}
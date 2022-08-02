package com.markoid.readerapp.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.markoid.readerapp.presentation.components.ReaderLogo
import com.markoid.readerapp.presentation.components.SignUpInstructions
import com.markoid.readerapp.presentation.components.SwitchFormButton
import com.markoid.readerapp.presentation.components.UserForm
import com.markoid.readerapp.presentation.enums.DataState.Data
import com.markoid.readerapp.presentation.enums.DataState.Error
import com.markoid.readerapp.presentation.enums.LoadingState
import com.markoid.readerapp.presentation.extensions.navigateAndRemoveFromStack
import com.markoid.readerapp.presentation.navigation.NavScreens
import com.markoid.readerapp.presentation.viewmodels.AuthViewModel

@Composable
fun AuthScreen(
  navController: NavController,
  authViewModel: AuthViewModel = hiltViewModel()
) {
  val showLoginForm = rememberSaveable { mutableStateOf(true) }
  val inRoute = rememberSaveable { mutableStateOf(false) }

  // Observe sign in result
  ObserveSignInResult(
    authViewModel = authViewModel,
    inRoute = inRoute,
    navController = navController
  )

  // Observe sign up result
  ObserveSignUpResult(
    authViewModel = authViewModel,
    inRoute = inRoute,
    navController = navController
  )

  // Start UI creation
  Surface(modifier = Modifier.fillMaxSize()) {
    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      modifier = Modifier
        .padding(12.dp)
        .verticalScroll(rememberScrollState())
    ) {
      ReaderLogo()
      SignUpInstructions(showLoginForm = showLoginForm)
      val loading = if (showLoginForm.value) authViewModel.signInObserver.getLoading()
      else authViewModel.signUpObserver.getLoading()
      UserForm(
        loading = loading.collectAsState(LoadingState.Dismiss).value == LoadingState.Show,
        isCreateAccount = showLoginForm.value.not()
      ) { user ->
        if (showLoginForm.value) {
          // Perform login
          authViewModel.signIn(user)
        } else {
          // Perform sign up
          authViewModel.signUp(user)
        }
      }
      Spacer(modifier = Modifier.height(15.dp))
      SwitchFormButton(showLoginForm = showLoginForm)
    }
  }
}

@Composable
fun ObserveSignInResult(
  authViewModel: AuthViewModel,
  inRoute: MutableState<Boolean>,
  navController: NavController
) {
  when (val signInResult = authViewModel.signInObserver
    .getResult()
    .collectAsState(null)
    .value
  ) {
    is Data -> {
      if (inRoute.value.not()) {
        navController.navigateAndRemoveFromStack(
          destination = NavScreens.Home.name,
          nodeToRemove = NavScreens.Authentication.name
        )
        inRoute.value = true
      }
    }
    is Error -> Toast.makeText(LocalContext.current, signInResult.error, Toast.LENGTH_LONG).show()
  }
}

@Composable
fun ObserveSignUpResult(
  authViewModel: AuthViewModel,
  inRoute: MutableState<Boolean>,
  navController: NavController
) {
  when (val signInResult = authViewModel.signUpObserver
    .getResult()
    .collectAsState(null)
    .value
  ) {
    is Data -> {
      if (inRoute.value.not()) {
        navController.navigateAndRemoveFromStack(
          destination = NavScreens.Home.name,
          nodeToRemove = NavScreens.Authentication.name
        )
        inRoute.value = true
      }
    }
    is Error -> Toast.makeText(LocalContext.current, signInResult.error, Toast.LENGTH_LONG).show()
  }
}
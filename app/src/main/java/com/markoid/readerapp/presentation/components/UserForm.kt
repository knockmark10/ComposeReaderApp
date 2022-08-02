package com.markoid.readerapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.markoid.readerapp.R
import com.markoid.readerapp.data.entities.UserFormEntry
import com.markoid.readerapp.presentation.actions.OnAuthDone

@OptIn(ExperimentalComposeUiApi::class)
@Preview(showBackground = true)
@Composable
fun UserForm(
  loading: Boolean = false,
  isCreateAccount: Boolean = false,
  onDone: OnAuthDone = { _ -> }
) {
  val email = rememberSaveable { mutableStateOf("") }
  val password = rememberSaveable { mutableStateOf("") }
  val passwordVisibility = rememberSaveable { mutableStateOf(false) }
  val passwordFocusRequest = FocusRequester.Default
  val keyboardController = LocalSoftwareKeyboardController.current
  val valid = remember(email.value, password.value) {
    email.value.trim().isNotEmpty() && password.value.trim().isNotEmpty()
  }

  Column(
    modifier = Modifier
      .height(250.dp)
      .background(MaterialTheme.colors.background),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    EmailInput(
      emailState = email,
      enabled = loading.not(),
      keyboardAction = KeyboardActions { passwordFocusRequest.requestFocus() }
    )
    PasswordInput(
      modifier = Modifier.focusRequester(passwordFocusRequest),
      passwordState = password,
      enabled = loading.not(),
      passwordVisibility = passwordVisibility,
      keyboardAction = KeyboardActions {
        if (valid.not()) return@KeyboardActions
        onDone(UserFormEntry(email.value.trim(), password.value))
        keyboardController?.hide()
      }
    )
    SubmitButton(
      text = if (isCreateAccount) stringResource(id = R.string.create_account)
      else stringResource(id = R.string.sign_in),
      loading = loading,
      validInputs = valid,
      onClick = {
        onDone(UserFormEntry(email.value, password.value))
        keyboardController?.hide()
      }
    )
  }
}



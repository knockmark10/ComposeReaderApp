package com.markoid.readerapp.presentation.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.markoid.readerapp.R

@Composable
fun PasswordInput(
  modifier: Modifier,
  passwordState: MutableState<String>,
  enabled: Boolean,
  passwordVisibility: MutableState<Boolean>,
  keyboardAction: KeyboardActions,
  imeAction: ImeAction = ImeAction.Done
) {
  val visualTransformation = if (passwordVisibility.value) VisualTransformation.None
  else PasswordVisualTransformation()

  InputField(
    modifier = modifier,
    valueState = passwordState,
    label = stringResource(id = R.string.password),
    enabled = enabled,
    keyboardType = KeyboardType.Password,
    imeAction = imeAction,
    keyboardAction = keyboardAction,
    visualTransformation = visualTransformation,
    trailingIcon = { PasswordVisibility(passwordVisibility = passwordVisibility) }
  )
}

@Composable
fun PasswordVisibility(passwordVisibility: MutableState<Boolean>) {
  val visible = passwordVisibility.value
  IconButton(onClick = { passwordVisibility.value = visible.not() }) {
    Icons.Default.Close
  }
}

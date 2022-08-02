package com.markoid.readerapp.presentation.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.markoid.readerapp.R

@Composable
fun EmailInput(
  modifier: Modifier = Modifier,
  emailState: MutableState<String>,
  labelId: String = stringResource(id = R.string.email),
  enabled: Boolean = true,
  imeAction: ImeAction = ImeAction.Next,
  keyboardAction: KeyboardActions = KeyboardActions.Default
) {
  InputField(
    modifier = modifier,
    valueState = emailState,
    label = labelId,
    enabled = enabled,
    keyboardType = KeyboardType.Email,
    imeAction = imeAction,
    keyboardAction = keyboardAction
  )
}
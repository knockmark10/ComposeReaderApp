package com.markoid.readerapp.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.input.VisualTransformation.Companion
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import retrofit2.Retrofit

@Composable
fun InputField(
  modifier: Modifier = Modifier,
  valueState: MutableState<String>,
  label: String,
  enabled: Boolean,
  isSingleLine: Boolean = true,
  keyboardType: KeyboardType = KeyboardType.Text,
  imeAction: ImeAction = ImeAction.Next,
  keyboardAction: KeyboardActions = KeyboardActions.Default,
  visualTransformation: VisualTransformation = VisualTransformation.None,
  trailingIcon: @Composable (() -> Unit)? = null
) {
  OutlinedTextField(
    value = valueState.value,
    onValueChange = { valueState.value = it },
    label = { Text(text = label) },
    singleLine = isSingleLine,
    textStyle = TextStyle(
      fontSize = 18.sp,
      color = MaterialTheme.colors.onBackground
    ),
    modifier = modifier
      .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
      .fillMaxWidth(),
    enabled = enabled,
    keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
    keyboardActions = keyboardAction,
    visualTransformation = visualTransformation,
    trailingIcon = trailingIcon
  )
}
package com.markoid.readerapp.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.markoid.readerapp.R.string

@Composable
fun SignUpInstructions(
  showLoginForm: MutableState<Boolean>
) {
  Text(
    text = if (showLoginForm.value) "" else stringResource(id = string.sign_up_instructions),
    modifier = if (showLoginForm.value) Modifier else Modifier.padding(bottom = 20.dp, top = 20.dp)
  )
}
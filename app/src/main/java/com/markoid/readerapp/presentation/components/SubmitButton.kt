package com.markoid.readerapp.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SubmitButton(
  text: String,
  loading: Boolean,
  validInputs: Boolean,
  onClick: () -> Unit = {}
) {
  Button(
    onClick = onClick,
    // Will enable the button if is not loading and input has valid entries
    enabled = loading.not() && validInputs,
    modifier = Modifier
      .padding(3.dp)
      .fillMaxWidth(),
    shape = CircleShape
  ) {
    if (loading) CircularProgressIndicator(modifier = Modifier.size(25.dp))
    else Text(text = text, modifier = Modifier.padding(5.dp))
  }
}
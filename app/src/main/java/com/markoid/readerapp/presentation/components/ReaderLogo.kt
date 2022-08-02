package com.markoid.readerapp.presentation.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.markoid.readerapp.R.string

@Composable
fun ReaderLogo(modifier: Modifier = Modifier) {
  Text(
    text = stringResource(id = string.splash_title),
    style = MaterialTheme.typography.h3,
    color = Color.Red.copy(alpha = 0.5f),
    modifier = modifier
  )
}
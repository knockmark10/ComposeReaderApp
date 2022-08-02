package com.markoid.readerapp.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.markoid.readerapp.R.string
import com.markoid.readerapp.presentation.theme.Teal200

@Composable
fun SwitchFormButton(showLoginForm: MutableState<Boolean>) {
  Column(
    modifier = Modifier
      .padding(8.dp)
      .fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    Text(
      text = buildAnnotatedString {
        val firstText =
          if (showLoginForm.value) stringResource(id = string.new_user)
          else stringResource(id = string.already_account)
        val secondText =
          if (showLoginForm.value) stringResource(id = string.create_account)
          else stringResource(id = string.sign_in)
        append(firstText)
        append(" ")
        withStyle(
          style = SpanStyle(
            color = MaterialTheme.colors.secondaryVariant,
            fontWeight = FontWeight.Bold
          )
        ) { append(secondText) }
      },
      modifier = Modifier.clickable { showLoginForm.value = !showLoginForm.value }
    )
  }
}
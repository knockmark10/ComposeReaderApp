package com.markoid.readerapp.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import com.markoid.readerapp.R

typealias OnSearch = (String) -> Unit

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchForm(
  modifier: Modifier = Modifier,
  loading: Boolean = false,
  hint: String = stringResource(id = R.string.search),
  onSearch: OnSearch = {}
) {
  Column(
    modifier = modifier
  ) {
    val searchQueryState = rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val valid = remember(searchQueryState.value) {
      searchQueryState.value.trim().isNotEmpty()
    }

    InputField(
      valueState = searchQueryState,
      label = hint,
      enabled = true,
      keyboardAction = KeyboardActions {
        if (valid.not()) return@KeyboardActions
        onSearch(searchQueryState.value.trim())
        searchQueryState.value = ""
        keyboardController?.hide()
      }
    )
  }
}
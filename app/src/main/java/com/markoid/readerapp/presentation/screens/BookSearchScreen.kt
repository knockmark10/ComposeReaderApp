package com.markoid.readerapp.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.markoid.readerapp.domain.entities.ScreenState.Data
import com.markoid.readerapp.domain.entities.ScreenState.Error
import com.markoid.readerapp.domain.entities.ScreenState.Loading
import com.markoid.readerapp.presentation.components.BookResultsList
import com.markoid.readerapp.presentation.components.ReaderAppBar
import com.markoid.readerapp.presentation.components.SearchForm
import com.markoid.readerapp.presentation.enums.isShowing
import com.markoid.readerapp.presentation.viewmodels.BookSearchViewModel

@Composable
fun BookSearchScreen(
  navController: NavController,
  bookSearchViewModel: BookSearchViewModel = hiltViewModel()
) {
  Scaffold(
    topBar = {
      ReaderAppBar(
        title = "Search",
        showProfile = false,
        showBackArrow = true,
        navController = navController,
        onBackArrowClicked = { navController.popBackStack() }
      )
    }
  ) {
    Surface {
      Column {
        SearchForm(
          modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
          onSearch = { query -> bookSearchViewModel.searchBook(query) }
        )
        Spacer(modifier = Modifier.height(13.dp))
        when (val result = bookSearchViewModel.listOfBooks.value) {
          is Data -> BookResultsList(navController = navController, results = result.data)
          is Error -> Text(text = result.error.message ?: "Error")
          is Loading -> if (result.loading.isShowing) {
            Column(
              modifier = Modifier.fillMaxSize(),
              horizontalAlignment = Alignment.CenterHorizontally
            ) {
              CircularProgressIndicator()
            }
          }
        }
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
private fun BookSearchScreenPreview() {
  BookSearchScreen(navController = NavController(LocalContext.current))
}
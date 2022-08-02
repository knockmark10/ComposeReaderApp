package com.markoid.readerapp.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.markoid.readerapp.domain.entities.ScreenState.Data
import com.markoid.readerapp.domain.entities.ScreenState.Error
import com.markoid.readerapp.domain.entities.ScreenState.Loading
import com.markoid.readerapp.presentation.components.BookDetailComponent
import com.markoid.readerapp.presentation.components.LinearLoadingWithText
import com.markoid.readerapp.presentation.components.ReaderAppBar
import com.markoid.readerapp.presentation.viewmodels.BookDetailsViewModel

@Composable
fun BookDetailsScreen(
  navController: NavController,
  bookId: String,
  bookViewModel: BookDetailsViewModel = hiltViewModel()
) {
  LaunchedEffect(key1 = true) { bookViewModel.getBookById(bookId) }

  Scaffold(topBar = {
    ReaderAppBar(
      title = "Book Details",
      showProfile = false,
      showBackArrow = true,
      navController = navController
    )
  }) {
    Surface(
      modifier = Modifier
        .padding(3.dp)
        .fillMaxSize()
    ) {
      Column(
        modifier = Modifier.padding(top = 12.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        when (val result = bookViewModel.bookDetailsState.value) {
          is Data -> BookDetailComponent(book = result.data, navController = navController)
          is Error -> Text(text = "Error $bookId")
          is Loading -> LinearLoadingWithText()
        }
      }
    }
  }
}
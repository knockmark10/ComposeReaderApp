package com.markoid.readerapp.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.markoid.readerapp.domain.entities.Book
import com.markoid.readerapp.domain.entities.ScreenState
import com.markoid.readerapp.presentation.components.ReaderAppBar
import com.markoid.readerapp.presentation.viewmodels.HomeScreenViewModel

@Composable
fun BookUpdateScreen(
  navController: NavController,
  bookId: String,
  viewModel: HomeScreenViewModel = hiltViewModel()
) {
  Scaffold(
    topBar = {
      ReaderAppBar(
        title = "Update Book",
        navController = navController,
        showProfile = false,
        showBackArrow = true
      )
    }
  ) {
    val bookInfo = produceState<ScreenState<List<Book>>?>(
      initialValue = ScreenState.Data(emptyList())
    ) {
      value = viewModel.bookState.value
    }.value

    Surface(
      modifier = Modifier
        .fillMaxSize()
        .padding(3.dp)
    ) {
      Column(
        modifier = Modifier.padding(top = 3.dp),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {

      }
    }
  }
}
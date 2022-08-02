package com.markoid.readerapp.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.markoid.readerapp.domain.entities.Book
import com.markoid.readerapp.domain.entities.ScreenState.Data
import com.markoid.readerapp.domain.entities.ScreenState.Error
import com.markoid.readerapp.domain.entities.ScreenState.Loading
import com.markoid.readerapp.presentation.components.HomeContent
import com.markoid.readerapp.presentation.components.HomeFloatingActionButton
import com.markoid.readerapp.presentation.components.LinearLoadingWithText
import com.markoid.readerapp.presentation.components.ReaderAppBar
import com.markoid.readerapp.presentation.navigation.NavScreens
import com.markoid.readerapp.presentation.viewmodels.HomeScreenViewModel

@Composable
fun HomeScreen(
  navController: NavController,
  viewModel: HomeScreenViewModel = hiltViewModel()
) {
  LaunchedEffect(key1 = true) { viewModel.getBooksByUserId() }
  Scaffold(
    topBar = { ReaderAppBar(title = "Home", navController = navController) },
    floatingActionButton = {
      HomeFloatingActionButton { navController.navigate(NavScreens.Search.name) }
    }
  ) {
    when (val result = viewModel.bookState.value) {
      is Data -> SuccessContent(booksList = result.data, navController = navController)
      is Error -> FailureContent(result.error)
      is Loading -> LinearLoadingWithText(text = "Loading books")
    }

  }
}

@Composable
fun SuccessContent(
  booksList: List<Book>,
  navController: NavController
) {
  Surface(
    modifier = Modifier
      .fillMaxSize()
      .padding(12.dp)
  ) {
    HomeContent(
      navController = navController,
      listOfBooks = booksList
    )
  }
}

@Composable
fun FailureContent(error: Throwable) {
  Surface(
    modifier = Modifier
      .fillMaxSize()
      .padding(12.dp),
    content = { Text(text = error.localizedMessage.orEmpty()) }
  )
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
  HomeScreen(navController = NavController(LocalContext.current))
}



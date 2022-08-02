package com.markoid.readerapp.presentation.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.markoid.readerapp.domain.entities.Book
import com.markoid.readerapp.presentation.actions.OnBookClicked
import com.markoid.readerapp.presentation.navigation.NavScreens

@Composable
fun BookList(
  booksList: List<Book>,
  navController: NavController
) {
  HorizontalScrollableComponent(books = booksList) {
    navController.navigate(NavScreens.Update.name + "/${it.id}")
  }
}

@Composable
fun HorizontalScrollableComponent(
  books: List<Book>,
  onCardPressed: OnBookClicked = {}
) {
  val scrollState = rememberScrollState()
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .height(280.dp)
      .horizontalScroll(scrollState)
  ) {
    for (book in books) {
      BookItem(book = book) { onCardPressed(it) }
    }
  }
}

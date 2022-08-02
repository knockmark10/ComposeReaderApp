package com.markoid.readerapp.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.markoid.readerapp.domain.entities.Book
import com.markoid.readerapp.domain.entities.ScreenState.Data
import com.markoid.readerapp.domain.entities.ScreenState.Error
import com.markoid.readerapp.domain.entities.ScreenState.Loading
import com.markoid.readerapp.presentation.viewmodels.BookDetailsViewModel

@Composable
fun BookDetailComponent(
  book: Book,
  navController: NavController
) {
  Card(
    modifier = Modifier.padding(34.dp),
    shape = CircleShape,
    elevation = 4.dp
  ) {
    AsyncImage(
      modifier = Modifier
        .size(90.dp)
        .padding(1.dp),
      model = ImageRequest.Builder(LocalContext.current)
        .data(book.coverUrl)
        .crossfade(true)
        .build(),
      contentDescription = "Book Image",
      contentScale = ContentScale.FillBounds
    )
  }
  Text(
    text = book.title,
    style = MaterialTheme.typography.h6,
    overflow = TextOverflow.Ellipsis,
    maxLines = 19
  )
  Text(text = "Authors: ${book.author}")
  Text(text = "Page Count: ${book.numberOfPages}")
  Text(text = "Categories: ${book.categories}")
  Spacer(modifier = Modifier.height(10.dp))
  ScrollableDescription(description = book.description)
  Buttons(book = book, navController = navController)
}

@Composable
fun ScrollableDescription(description: String) {
  val cleanDescription =
    HtmlCompat.fromHtml(description, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()

  val displayMetrics = LocalContext.current.resources.displayMetrics

  Surface(
    modifier = Modifier
      .height(displayMetrics.heightPixels.dp.times(0.09f))
      .padding(4.dp),
    shape = RectangleShape,
    border = BorderStroke(1.dp, Color.DarkGray)
  ) {
    LazyColumn(modifier = Modifier.padding(3.dp)) {
      item { Text(text = cleanDescription) }
    }
  }
}

@Composable
fun Buttons(
  book: Book,
  navController: NavController,
  bookViewModel: BookDetailsViewModel = hiltViewModel()
) {
  Row(
    modifier = Modifier.padding(top = 6.dp),
    horizontalArrangement = Arrangement.SpaceAround
  ) {
    RoundedButton(
      label = "Save",
      onClick = { bookViewModel.saveBook(book) }
    )
    when (val result = bookViewModel.saveBookState.value) {
      is Data -> navController.popBackStack()
      is Error -> Text(text = result.error.message.orEmpty())
      is Loading -> LinearLoadingWithText(text = "Saving book")
    }
    Spacer(modifier = Modifier.width(25.dp))
    RoundedButton(
      label = "Cancel",
      onClick = { navController.popBackStack() }
    )
  }
}
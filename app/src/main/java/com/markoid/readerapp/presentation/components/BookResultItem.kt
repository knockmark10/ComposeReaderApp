package com.markoid.readerapp.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.markoid.readerapp.domain.entities.Book
import com.markoid.readerapp.presentation.navigation.NavScreens
import com.markoid.readerapp.presentation.navigation.goTo

@Composable
fun BookResultItem(
  book: Book,
  navController: NavController
) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .height(100.dp)
      .padding(3.dp)
      .clickable { navController.goTo(NavScreens.BookDetails(book.id)) },
    shape = RectangleShape,
    elevation = 7.dp
  ) {
    Row(modifier = Modifier.padding(5.dp)) {
      AsyncImage(
        modifier = Modifier
          .width(80.dp)
          .fillMaxHeight()
          .padding(end = 4.dp),
        model = ImageRequest.Builder(LocalContext.current)
          .data(book.coverUrl)
          .crossfade(true)
          .build(),
        contentDescription = "Result Book Image",
        contentScale = ContentScale.Crop
      )
      Column {
        Text(
          text = book.title,
          overflow = TextOverflow.Ellipsis
        )
        Text(
          text = "Author: ${book.author}",
          overflow = TextOverflow.Clip,
          style = MaterialTheme.typography.caption
        )
      }
    }
  }
}
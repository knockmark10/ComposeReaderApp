package com.markoid.readerapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.markoid.readerapp.domain.entities.Book
import com.markoid.readerapp.presentation.actions.OnBookClicked

@Composable
fun BookItem(
  book: Book,
  onClick: OnBookClicked = {}
) {
  val displayMetrics = LocalContext.current.resources.displayMetrics
  val screenWidth = displayMetrics.widthPixels / displayMetrics.density
  val spacing = 10.dp
  Card(
    shape = RoundedCornerShape(29.dp),
    backgroundColor = Color.White,
    elevation = 6.dp,
    modifier = Modifier
      .padding(16.dp)
      .height(242.dp)
      .width(202.dp)
      .clickable { onClick(book) }
  ) {
    Column(
      modifier = Modifier
        .width(screenWidth.dp - (spacing * 2))
        .padding(2.dp),
      horizontalAlignment = Alignment.Start
    ) {
      Row(horizontalArrangement = Arrangement.Center) {
        Image(
          painter = rememberImagePainter(data = book.coverUrl),
          contentDescription = "Book image",
          modifier = Modifier
            .height(140.dp)
            .width(100.dp)
            .padding(4.dp)
            .clip(RoundedCornerShape(20))

        )
        Spacer(modifier = Modifier.width(50.dp))
        Column(
          modifier = Modifier.padding(top = 25.dp),
          verticalArrangement = Arrangement.Center,
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Icon(
            imageVector = Icons.Rounded.FavoriteBorder,
            contentDescription = "Favorite Icon",
            modifier = Modifier.padding(1.dp)
          )
          BookRating(score = 3.5)
        }
      }
      Text(
        text = book.title,
        modifier = Modifier.padding(4.dp),
        fontWeight = FontWeight.Bold,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis // ...
      )
      Text(
        text = book.author,
        modifier = Modifier.padding(4.dp),
        style = MaterialTheme.typography.caption
      )
    }
    Row(
      horizontalArrangement = Arrangement.End,
      verticalAlignment = Alignment.Bottom
    ) {
      RoundedButton(label = "Reading", radius = 70)
    }
  }
}

@Preview(showBackground = true)
@Composable
fun BookItemPreview() {
  BookItem(Book())
}
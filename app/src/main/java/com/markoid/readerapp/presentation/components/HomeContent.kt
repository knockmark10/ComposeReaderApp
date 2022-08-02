package com.markoid.readerapp.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.markoid.readerapp.R
import com.markoid.readerapp.domain.entities.Book
import com.markoid.readerapp.presentation.navigation.NavScreens
import com.markoid.readerapp.presentation.viewmodels.AuthViewModel

@Composable
fun HomeContent(
  listOfBooks: List<Book>,
  navController: NavController,
  authViewModel: AuthViewModel = hiltViewModel()
) {
  LaunchedEffect(key1 = true) { authViewModel.getCurrentUser() }
  val nameState = authViewModel.userProfileObserver
    .getResult()
    .collectAsState(null)
  Column(
    modifier = Modifier
      .padding(2.dp),
    verticalArrangement = Arrangement.Top
    // verticalArrangement = Arrangement.Arrangement.SpaceEvenly
  ) {
    Row(
      modifier = Modifier.align(Alignment.Start),
      verticalAlignment = Alignment.CenterVertically
    ) {
      TitleSection(label = stringResource(id = R.string.ongoing_reading_activity))
      Spacer(modifier = Modifier.fillMaxWidth(0.7f))
      Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
          imageVector = Icons.Filled.AccountCircle,
          contentDescription = "Profile icon",
          modifier = Modifier
            .size(45.dp)
            .clickable { navController.navigate(NavScreens.Stats.name) },
          tint = MaterialTheme.colors.secondaryVariant
        )
        ProfileName(nameState = nameState)
        Divider()
      }
    }
    BookItem(Book("21", "El Psicoanalista", "John Katzenbach", "Very very good"))
    TitleSection(label = stringResource(id = R.string.reading_list))
    BookList(booksList = listOfBooks, navController = navController)
  }
}

@Preview(showBackground = true)
@Composable
fun HomeContentPreview() {
  HomeContent(
    navController = NavController(LocalContext.current),
    listOfBooks = emptyList()
  )
}
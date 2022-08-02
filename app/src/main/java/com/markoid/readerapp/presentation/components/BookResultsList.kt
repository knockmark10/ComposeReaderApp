package com.markoid.readerapp.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.markoid.readerapp.domain.entities.Book

@Composable
fun BookResultsList(
  navController: NavController,
  results: List<Book>
) {
  LazyColumn(
    modifier = Modifier.fillMaxSize(),
    contentPadding = PaddingValues(16.dp)
  ) {
    items(results) { result -> BookResultItem(result, navController) }
  }
}
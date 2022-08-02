package com.markoid.readerapp.presentation.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.markoid.readerapp.presentation.actions.OnHomeFabTapAction

@Composable
fun HomeFloatingActionButton(
  onClick: OnHomeFabTapAction = {}
) {
  FloatingActionButton(
    onClick = { onClick("") },
    shape = RoundedCornerShape(50.dp),
    backgroundColor = Color(0xff92CBDF)
  ) {
    Icon(
      imageVector = Icons.Default.Add,
      contentDescription = "Add a book",
      tint = Color.White
    )
  }
}
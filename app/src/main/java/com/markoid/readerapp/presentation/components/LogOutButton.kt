package com.markoid.readerapp.presentation.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

typealias OnLogOutClicked = () -> Unit

@Composable
fun LogOutButton(
  onLogOutClicked: OnLogOutClicked
) {
  IconButton(onClick = onLogOutClicked) {
    Icon(
      imageVector = Icons.Default.Logout,
      contentDescription = "Logout",
      tint = Color.White
    )
  }
}
package com.markoid.readerapp.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.BookOnline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.markoid.readerapp.presentation.navigation.NavScreens
import com.markoid.readerapp.presentation.theme.Purple500
import com.markoid.readerapp.presentation.viewmodels.AuthViewModel

typealias OnBackClicked = () -> Unit

@Composable
fun ReaderAppBar(
  title: String,
  showProfile: Boolean = true,
  showBackArrow: Boolean = false,
  navController: NavController,
  authViewModel: AuthViewModel = hiltViewModel(),
  onBackArrowClicked: OnBackClicked = { navController.popBackStack() }
) {
  TopAppBar(
    title = {
      Row(verticalAlignment = Alignment.CenterVertically) {
        when {
          showProfile -> {
            Icon(
              imageVector = Icons.Default.BookOnline,
              contentDescription = "Logo Icon",
              modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .scale(0.9f)
            )
          }
          showBackArrow -> {
            Icon(
              imageVector = Icons.Default.ArrowBack,
              contentDescription = "Back Icon",
              modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .clickable { onBackArrowClicked() }
            )
            Spacer(modifier = Modifier.width(20.dp))
          }
        }

        Text(
          text = title,
          color = Color.White,
          style = TextStyle(fontSize = 20.sp)
        )
        Spacer(modifier = Modifier.width(150.dp))

      }
    },
    actions = {
      if (showProfile) LogOutButton {
        authViewModel.signOut()
        navController.navigate(NavScreens.Authentication.name)
      } else Box {}
    },
    backgroundColor = Purple500
  )
}
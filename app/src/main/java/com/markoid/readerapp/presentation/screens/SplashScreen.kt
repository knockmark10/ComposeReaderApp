package com.markoid.readerapp.presentation.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.markoid.readerapp.R
import com.markoid.readerapp.presentation.components.ReaderLogo
import com.markoid.readerapp.presentation.extensions.navigateAndRemoveFromStack
import com.markoid.readerapp.presentation.navigation.NavScreens
import com.markoid.readerapp.presentation.viewmodels.AuthViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
  navController: NavController,
  authViewModel: AuthViewModel = hiltViewModel()
) {
  // Creating animations
  val scale = remember { Animatable(initialValue = 0f) }
  // Launch splash scale animation
  LaunchedEffect(key1 = true) {
    // Get actual value from validation
    scale.animateTo(
      targetValue = 0.9f,
      animationSpec = tween(
        durationMillis = 800,
        easing = { OvershootInterpolator(8f).getInterpolation(it) }
      )
    )
    // After animation ends, we'll wait for 1 second
    delay(1000L)
    // Trigger user-authenticated validation
    authViewModel.getUserAuthenticatedFlow()
    // Collect results from validation
    authViewModel.userAuthenticated.collect { isAuthenticated ->
      // Then we'll take the user to sign in or home screen
      val destination = when (isAuthenticated) {
        true -> NavScreens.Home
        false -> NavScreens.Authentication
        else -> null
      }
      // Make sure there is an actual destination
      destination?.let {
        // Navigate to the destination and remove splash from back stack
        navController.navigateAndRemoveFromStack(
          destination = destination.name,
          nodeToRemove = NavScreens.Splash.name
        )
      }
    }
  }

  // Creating UI
  Surface(
    modifier = Modifier
      .padding(15.dp)
      .size(330.dp)
      .scale(scale.value),
    shape = CircleShape,
    color = Color.White,
    border = BorderStroke(width = 2.dp, color = Color.LightGray)
  ) {
    Column(
      modifier = Modifier,
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      ReaderLogo(modifier = Modifier.padding(bottom = 6.dp))
      Spacer(modifier = Modifier.height(15.dp))
      Text(
        text = stringResource(id = R.string.splash_subtitle),
        style = MaterialTheme.typography.h5,
        color = Color.LightGray
      )
      Spacer(modifier = Modifier.height(15.dp))
    }
  }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
  SplashScreen(navController = NavController(LocalContext.current))
}
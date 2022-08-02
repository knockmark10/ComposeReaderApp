package com.markoid.readerapp.presentation.extensions

import androidx.navigation.NavController

fun NavController.navigateAndRemoveFromStack(destination: String, nodeToRemove: String) {
  navigate(route = destination) {
    popUpTo(route = nodeToRemove) { inclusive = true }
  }
}
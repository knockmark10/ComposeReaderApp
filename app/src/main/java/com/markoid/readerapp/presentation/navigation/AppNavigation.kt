package com.markoid.readerapp.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.markoid.readerapp.presentation.navigation.NavScreens.Authentication
import com.markoid.readerapp.presentation.navigation.NavScreens.BookDetails
import com.markoid.readerapp.presentation.navigation.NavScreens.BookUpdate
import com.markoid.readerapp.presentation.navigation.NavScreens.Home
import com.markoid.readerapp.presentation.navigation.NavScreens.Search
import com.markoid.readerapp.presentation.navigation.NavScreens.Splash
import com.markoid.readerapp.presentation.navigation.NavScreens.Stats
import com.markoid.readerapp.presentation.navigation.NavScreens.Update
import com.markoid.readerapp.presentation.screens.AuthScreen
import com.markoid.readerapp.presentation.screens.BookDetailsScreen
import com.markoid.readerapp.presentation.screens.BookSearchScreen
import com.markoid.readerapp.presentation.screens.BookUpdateScreen
import com.markoid.readerapp.presentation.screens.HomeScreen
import com.markoid.readerapp.presentation.screens.SplashScreen
import com.markoid.readerapp.presentation.screens.StatsScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavigation() {
  // Create a controller
  val navController = rememberNavController()

  // Create the navigation host
  NavHost(
    navController = navController,
    startDestination = NavScreens.Splash.name,
    /*enterTransition = { _, _ ->
      slideInHorizontally(initialOffsetX = { it / 2 }, animationSpec = tween(500))
    },
    exitTransition = { _, _ ->
      slideOutHorizontally(targetOffsetX = { it / 2 }, animationSpec = tween(500))
    }*/
  ) {
    composable(
      route = NavScreens.Splash.name,

      ) {
      SplashScreen(navController = navController)
    }

    composable(route = Home.name) {
      HomeScreen(navController = navController)
    }

    val detailName = BookDetails().name
    composable(
      route = "$detailName/{bookId}",
      arguments = listOf(
        navArgument(name = "bookId", builder = { type = NavType.StringType })
      )
    ) { backStackEntry ->
      val bookId = backStackEntry.arguments?.getString("bookId") ?: ""
      BookDetailsScreen(navController = navController, bookId = bookId)
    }

    val updateName = BookUpdate().name
    composable(
      route = "$updateName/{bookId}",
      arguments = listOf(navArgument(name = "bookId", builder = { type = NavType.StringType }))
    ) {
      val bookId = it.arguments?.getString("bookId")
      BookUpdateScreen(navController = navController, bookId = bookId)
    }

    composable(route = NavScreens.Authentication.name) {
      AuthScreen(navController = navController)
    }

    composable(route = NavScreens.Stats.name) {
      StatsScreen(navController = navController)
    }

    composable(route = NavScreens.Search.name) {
      BookSearchScreen(navController = navController)
    }
  }
}

fun NavController.goTo(screen: NavScreens) {
  when (screen) {
    Authentication,
    Home,
    Search,
    Splash,
    Stats,
    Update -> navigate(screen.name)
    is BookDetails -> navigate("${screen.name}/${screen.bookId}")
    is BookUpdate -> navigate("${screen.name}/${screen.bookId}")
  }
}
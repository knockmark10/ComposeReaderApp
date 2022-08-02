package com.markoid.readerapp.presentation.navigation

sealed class NavScreens(val name: String) {
  object Authentication : NavScreens("Authentication")
  data class BookDetails(val bookId: String = "") : NavScreens("BookDetails")
  data class BookUpdate(val bookId: String = "") : NavScreens("BookUpdate")
  object Home : NavScreens("Home")
  object Search : NavScreens("Search")
  object Splash : NavScreens("Splash")
  object Stats : NavScreens("Stats")
  object Update : NavScreens("Update")
}

/*enum class NavScreens {
  Authentication,
  BookDetails,
  BookUpdate,
  Home,
  Search,
  Splash,
  Stats,
  Update;

  companion object {
    fun fromRoute(route: String?): NavScreens = when (route?.substringBefore("/")) {
      BookDetails.name -> BookDetails
      BookUpdate.name -> BookUpdate
      Home.name -> Home
      Authentication.name -> Authentication
      Splash.name -> Splash
      Stats.name -> Stats
      Update.name -> Update
      null, "" -> Home
      else -> throw IllegalArgumentException("Route $route is not recognized")
    }
  }
}*/

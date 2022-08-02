package com.markoid.readerapp.domain.entities

const val USER_ID = "user_id"
const val DISPLAY_NAME = "display_name"
const val EMAIL = "email"
const val AVATAR_URL = "avatar_url"
const val QUOTE = "quote"
const val PROFESSION = "profession"

/**
 * This object will be used to store user data on Firebase Firestore.
 */
data class UserProfile(
  val id: String = "",
  val displayName: String = "",
  val email: String = "",
  val avatarUrl: String = "",
  val quote: String = "",
  val profession: String = ""
) {

  fun convertToMap(): MutableMap<String, Any> = mutableMapOf(
    USER_ID to id,
    DISPLAY_NAME to displayName,
    EMAIL to email,
    AVATAR_URL to avatarUrl,
    QUOTE to quote,
    PROFESSION to profession
  )
}

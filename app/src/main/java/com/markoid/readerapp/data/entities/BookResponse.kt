package com.markoid.readerapp.data.entities

import com.google.gson.annotations.SerializedName

data class BookResponse(

  @SerializedName("totalItems")
  private val _totalItems: Int? = null,

  @SerializedName("kind")
  private val _kind: String? = null,

  @SerializedName("items")
  private val _books: List<BookItem>? = null
) {

  val books: List<BookItem>
    get() = _books ?: emptyList()
}
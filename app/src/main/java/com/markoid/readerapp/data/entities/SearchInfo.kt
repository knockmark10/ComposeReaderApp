package com.markoid.readerapp.data.entities

import com.google.gson.annotations.SerializedName

data class SearchInfo(

  @SerializedName("textSnippet")
  private val _textSnippet: String? = null
) {

  val note: String
    get() = _textSnippet ?: ""
}
package com.markoid.readerapp.data.entities

import com.google.gson.annotations.SerializedName

data class ImageLinks(

  @SerializedName("thumbnail")
  private val _thumbnail: String? = null,

  @SerializedName("smallThumbnail")
  private val _smallThumbnail: String? = null
) {

  val coverUrl: String
    get() = _thumbnail ?: _smallThumbnail ?: ""
}
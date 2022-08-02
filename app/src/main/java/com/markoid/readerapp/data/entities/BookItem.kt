package com.markoid.readerapp.data.entities

import com.google.gson.annotations.SerializedName

data class BookItem(

  @SerializedName("saleInfo")
  private val _saleInfo: SaleInfo? = null,

  @SerializedName("searchInfo")
  private val _searchInfo: SearchInfo? = null,

  @SerializedName("kind")
  private val _kind: String? = null,

  @SerializedName("volumeInfo")
  private val _bookInfo: BookInfo? = null,

  @SerializedName("etag")
  private val _etag: String? = null,

  @SerializedName("id")
  private val _id: String? = null,

  @SerializedName("accessInfo")
  private val _accessInfo: AccessInfo? = null,

  @SerializedName("selfLink")
  private val _selfLink: String? = null
) {

  val id: String
    get() = _id ?: ""

  val bookInfo: BookInfo
    get() = _bookInfo ?: BookInfo()

  val searchInfo: SearchInfo
    get() = _searchInfo ?: SearchInfo()
}
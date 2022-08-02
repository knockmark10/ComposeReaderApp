package com.markoid.readerapp.data.entities

import com.google.gson.annotations.SerializedName

data class BookInfo(

  @SerializedName("industryIdentifiers")
  private val _industryIdentifiers: List<IndustryIdentifiersItem>? = null,

  @SerializedName("pageCount")
  private val _pageCount: Int? = null,

  @SerializedName("printType")
  private val _printType: String? = null,

  @SerializedName("readingModes")
  private val _readingModes: ReadingModes? = null,

  @SerializedName("previewLink")
  private val _previewLink: String? = null,

  @SerializedName("canonicalVolumeLink")
  private val _canonicalVolumeLink: String? = null,

  @SerializedName("description")
  private val _description: String? = null,

  @SerializedName("language")
  private val _language: String? = null,

  @SerializedName("title")
  private val _title: String? = null,

  @SerializedName("imageLinks")
  private val _imageLinks: ImageLinks? = null,

  @SerializedName("subtitle")
  private val _subtitle: String? = null,

  @SerializedName("panelizationSummary")
  private val _panelizationSummary: PanelizationSummary? = null,

  @SerializedName("publisher")
  private val _publisher: String? = null,

  @SerializedName("publishedDate")
  private val _publishedDate: String? = null,

  @SerializedName("categories")
  private val _categories: List<String>? = null,

  @SerializedName("maturityRating")
  private val _maturityRating: String? = null,

  @SerializedName("allowAnonLogging")
  private val _allowAnonLogging: Boolean? = null,

  @SerializedName("contentVersion")
  private val _contentVersion: String? = null,

  @SerializedName("authors")
  private val _authors: List<String>? = null,

  @SerializedName("infoLink")
  private val _infoLink: String? = null,

  @SerializedName("averageRating")
  private val _averageRating: Double? = null,

  @SerializedName("ratingsCount")
  private val _ratingsCount: Int? = null
) {

  val title: String
    get() = _title ?: ""

  val description: String
    get() = _description ?: ""

  val authors: String
    get() = _authors?.joinToString() ?: ""

  val images: ImageLinks
    get() = _imageLinks ?: ImageLinks()

  val pageCount: Int
    get() = _pageCount ?: 0

  val categories: String
    get() = _categories?.joinToString() ?: ""
}
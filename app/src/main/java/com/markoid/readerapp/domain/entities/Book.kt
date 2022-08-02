package com.markoid.readerapp.domain.entities

import com.google.firebase.Timestamp
import com.google.firebase.firestore.PropertyName

data class Book(

  var id: String = "",

  var title: String = "",

  var description: String = "",

  var author: String = "",

  var notes: String = "",

  @get:PropertyName("cover_url")
  @set:PropertyName("cover_url")
  var coverUrl: String = "https://images-na.ssl-images-amazon.com/images/I/516jG5F1OUL._SY344_BO1,204,203,200_QL70_ML2_.jpg",

  @get:PropertyName("number_of_pages")
  @set:PropertyName("number_of_pages")
  var numberOfPages: Int = 0,

  var categories: String = "",

  @get:PropertyName("user_id")
  @set:PropertyName("user_id")
  var userId: String = "",

  @get:PropertyName("started_reading")
  @set:PropertyName("started_reading")
  var startedReading: Timestamp? = null,

  @get:PropertyName("end_reading")
  @set:PropertyName("end_reading")
  var endReading: Timestamp? = null
)

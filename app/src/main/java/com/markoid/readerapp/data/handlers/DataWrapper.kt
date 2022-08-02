package com.markoid.readerapp.data.handlers

data class DataWrapper<T>(
  var data: T? = null,
  var exception: Throwable? = null
)
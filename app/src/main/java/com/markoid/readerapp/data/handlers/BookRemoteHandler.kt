package com.markoid.readerapp.data.handlers

import javax.inject.Inject

class BookRemoteHandler @Inject constructor() {

  fun handleResult(result: Boolean): DataWrapper<Boolean> = if (result) {
    DataWrapper(data = result)
  } else {
    DataWrapper(exception = IllegalStateException("Book could not be saved."))
  }
}
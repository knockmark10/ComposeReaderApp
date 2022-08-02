package com.markoid.readerapp.domain.entities

import com.markoid.readerapp.presentation.enums.LoadingState

sealed class ScreenState<out T> {
  data class Data<T>(val data: T) : ScreenState<T>()
  data class Loading(val loading: LoadingState) : ScreenState<Nothing>()
  data class Error(val error: Throwable) : ScreenState<Nothing>()
}



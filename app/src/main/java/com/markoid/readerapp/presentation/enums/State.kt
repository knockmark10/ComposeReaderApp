package com.markoid.readerapp.presentation.enums

typealias OnSuccessBlocking<T> = suspend (data: T) -> Unit
typealias OnErrorBlocking = suspend (error: Throwable) -> Unit
typealias OnLoadingBlocking = suspend (state: LoadingState) -> Unit

sealed class State<out T> {

  class Success<out T>(val successData: T) : State<T>()
  class Failure(val errorData: Throwable) : State<Nothing>()
  class Loading(val state: LoadingState) : State<Nothing>()

  suspend fun handleState(
    successBlock: OnSuccessBlocking<T> = {},
    failureBlock: OnErrorBlocking = {},
    loadingBlock: OnLoadingBlocking = {}
  ) {
    when (this@State) {
      is Success -> successBlock(successData)
      is Failure -> failureBlock(errorData)
      is Loading -> loadingBlock(state)
    }
  }
}

sealed class LoadingState {
  object Show : LoadingState()
  object Dismiss : LoadingState()
}

sealed class DataState<T> {
  class Data<T>(val data: T) : DataState<T>()
  class Error<T>(val error: String) : DataState<T>()
}

val LoadingState.isShowing: Boolean
  get() = this == LoadingState.Show
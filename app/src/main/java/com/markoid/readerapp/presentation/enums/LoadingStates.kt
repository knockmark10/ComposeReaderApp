package com.markoid.readerapp.presentation.enums

data class LoadingStates(
  val status: Status,
  val message: String? = null
) {

  companion object {
    val SUCCESS = LoadingStates(Status.SUCCESS)
    val FAILED = LoadingStates(Status.FAILED)
    val LOADING = LoadingStates(Status.LOADING)
    val IDLE = LoadingStates(Status.IDLE)
  }

  enum class Status {
    SUCCESS,
    FAILED,
    LOADING,
    IDLE
  }
}
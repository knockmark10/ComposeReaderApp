package com.markoid.readerapp.presentation.dispatchers

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {
  val main: CoroutineDispatcher
  val default: CoroutineDispatcher
  val io: CoroutineDispatcher
  val unconfined: CoroutineDispatcher
}
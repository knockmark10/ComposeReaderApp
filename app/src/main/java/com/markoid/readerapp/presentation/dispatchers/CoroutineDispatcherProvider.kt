package com.markoid.readerapp.presentation.dispatchers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class CoroutineDispatcherProvider
@Inject constructor() : DispatcherProvider {
  override val main: CoroutineDispatcher = Dispatchers.Main
  override val default: CoroutineDispatcher = Dispatchers.Default
  override val io: CoroutineDispatcher = Dispatchers.IO
  override val unconfined: CoroutineDispatcher = Dispatchers.Unconfined
}
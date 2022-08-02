package com.markoid.readerapp.presentation.observers

import com.markoid.readerapp.domain.usecases.UseCase
import com.markoid.readerapp.presentation.dispatchers.DispatcherProvider
import com.markoid.readerapp.presentation.enums.DataState
import com.markoid.readerapp.presentation.enums.LoadingState
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

/**
 * Class that will handle the use case execution
 */
class UseCaseObserver<Result, Request>(
  private val baseUseCase: UseCase<Result, Request>,
  private val dispatcherProvider: DispatcherProvider,
  private val coroutineScope: CoroutineScope
) {

  private val parentJob: CompletableJob = SupervisorJob()

  private val useCaseInvocationContext: CoroutineContext
    get() = parentJob + dispatcherProvider.io

  private val senderLiveData: MutableSharedFlow<DataState<Result>> = MutableSharedFlow()

  private val loadingLiveData: MutableSharedFlow<LoadingState> = MutableSharedFlow()

  /**
   * In charge of executing the use case, and handling the state emitted by it.
   */
  fun execute(request: Request): UseCaseObserver<Result, Request> {
    // Launch one job to collect the events emitted by sender
    coroutineScope.launch(dispatcherProvider.io) {
      // Collect the events from the use case
      baseUseCase.receiver.collect {
        // Process the state handling inside main thread
        withContext(dispatcherProvider.main) {
          // Handle the events emitted by the use case
          it.handleState(
            successBlock = { state ->
              senderLiveData.emit(DataState.Data(state))
            },
            failureBlock = { throwable ->
              val error = throwable.localizedMessage ?: throwable.message ?: "Error"
              senderLiveData.emit(DataState.Error(error))
            },
            loadingBlock = { loading ->
              loadingLiveData.emit(loading)
            }
          )
        }
      }
    }
    // Launch another job to execute main use case with request provided
    coroutineScope.launch(useCaseInvocationContext) { baseUseCase.invoke(request) }
    return this
  }

  /**
   * Provides notifications for the data flow.
   */
  fun getResult(): MutableSharedFlow<DataState<Result>> = senderLiveData

  /**
   * Provides notification for the loading states
   */
  fun getLoading(): MutableSharedFlow<LoadingState> = loadingLiveData

  /**
   * Stop the use case execution when it's no longer needed.
   */
  fun dispose(): Boolean = if (parentJob.isCancelled.not()) {
    parentJob.cancel()
    true
  } else {
    false
  }
}

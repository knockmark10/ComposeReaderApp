package com.markoid.readerapp.presentation.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.markoid.readerapp.domain.entities.Book
import com.markoid.readerapp.domain.entities.ScreenState
import com.markoid.readerapp.domain.usecases.GetUserBooksUseCase
import com.markoid.readerapp.presentation.dispatchers.DispatcherProvider
import com.markoid.readerapp.presentation.enums.LoadingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel
@Inject constructor(
  private val getUserBooksUseCase: GetUserBooksUseCase,
  private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

  private val _booksState: MutableState<ScreenState<List<Book>>?> = mutableStateOf(null)
  val bookState: State<ScreenState<List<Book>>?> = _booksState

  fun getBooksByUserId() = viewModelScope.launch(dispatcherProvider.io) {
    _booksState.value = ScreenState.Loading(LoadingState.Show)
    val result = getUserBooksUseCase.invoke()
    if (result.data != null) {
      _booksState.value = ScreenState.Data(result.data!!)
    } else {
      _booksState.value = ScreenState.Error(result.exception!!)
    }
  }
}
package com.markoid.readerapp.presentation.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.markoid.readerapp.domain.entities.Book
import com.markoid.readerapp.domain.entities.ScreenState
import com.markoid.readerapp.domain.repositories.BookRepository
import com.markoid.readerapp.presentation.dispatchers.DispatcherProvider
import com.markoid.readerapp.presentation.enums.LoadingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookSearchViewModel
@Inject constructor(
  private val bookRepository: BookRepository,
  private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

  val listOfBooks: MutableState<ScreenState<List<Book>>?> = mutableStateOf(null)

  fun searchBook(query: String) = viewModelScope.launch(dispatcherProvider.io) {
    if (query.isEmpty()) return@launch
    listOfBooks.value = ScreenState.Loading(LoadingState.Show)
    val response = bookRepository.searchBooks(query)
    listOfBooks.value = ScreenState.Loading(LoadingState.Dismiss)
    when {
      response.data != null -> listOfBooks.value = ScreenState.Data(response.data!!)
      response.exception != null -> listOfBooks.value = ScreenState.Error(response.exception!!)
    }
  }
}
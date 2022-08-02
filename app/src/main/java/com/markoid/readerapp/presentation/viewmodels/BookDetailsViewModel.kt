package com.markoid.readerapp.presentation.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.markoid.readerapp.domain.entities.Book
import com.markoid.readerapp.domain.entities.ScreenState
import com.markoid.readerapp.domain.repositories.BookRepository
import com.markoid.readerapp.domain.usecases.SaveBookUseCase
import com.markoid.readerapp.presentation.dispatchers.DispatcherProvider
import com.markoid.readerapp.presentation.enums.LoadingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailsViewModel
@Inject constructor(
  private val saveBookUseCase: SaveBookUseCase,
  private val bookRepository: BookRepository,
  private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

  val bookDetailsState: MutableState<ScreenState<Book>?> = mutableStateOf(null)

  val saveBookState: MutableState<ScreenState<Boolean>?> = mutableStateOf(null)

  /* THIS IS ONE WAY OF DOING THINGS */
  fun getBookById(bookId: String) = viewModelScope.launch(dispatcherProvider.io) {
    bookDetailsState.value = ScreenState.Loading(LoadingState.Show)
    val bookResponse = bookRepository.getBookInfo(bookId)
    bookDetailsState.value = ScreenState.Loading(LoadingState.Dismiss)
    if (bookResponse.data != null) {
      bookDetailsState.value = ScreenState.Data(bookResponse.data!!)
    } else {
      bookDetailsState.value = ScreenState.Error(bookResponse.exception!!)
    }
  }

  /* THIS IS THE SECOND WAY OF DOING THINGS */
  // suspend fun getBookById(bookId: String) = bookRepository.getBookInfo(bookId)

  fun saveBook(book: Book) = viewModelScope.launch(dispatcherProvider.io) {
    saveBookState.value = ScreenState.Loading(LoadingState.Show)
    val result = saveBookUseCase.invoke(book)
    saveBookState.value = ScreenState.Loading(LoadingState.Dismiss)
    if (result.data != null) {
      saveBookState.value = ScreenState.Data(result.data!!)
    } else {
      saveBookState.value = ScreenState.Error(result.exception!!)
    }
  }
}
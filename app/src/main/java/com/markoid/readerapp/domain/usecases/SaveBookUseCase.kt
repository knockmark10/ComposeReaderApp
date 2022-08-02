package com.markoid.readerapp.domain.usecases

import com.markoid.readerapp.data.handlers.DataWrapper
import com.markoid.readerapp.domain.entities.Book
import com.markoid.readerapp.domain.repositories.AuthRepository
import com.markoid.readerapp.domain.repositories.BookRepository
import javax.inject.Inject

class SaveBookUseCase
@Inject constructor(
  private val authRepository: AuthRepository,
  private val bookRepository: BookRepository
) {

  suspend operator fun invoke(book: Book): DataWrapper<Boolean> {
    val userId = authRepository.getCurrentUser().id
    return bookRepository.saveBook(book.copy(userId = userId))
  }
}
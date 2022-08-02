package com.markoid.readerapp.domain.usecases

import com.markoid.readerapp.data.handlers.DataWrapper
import com.markoid.readerapp.domain.entities.Book
import com.markoid.readerapp.domain.repositories.AuthRepository
import com.markoid.readerapp.domain.repositories.BookRepository
import javax.inject.Inject

class GetUserBooksUseCase
@Inject constructor(
  private val bookRepository: BookRepository,
  private val authRepository: AuthRepository
) {

  suspend operator fun invoke(): DataWrapper<List<Book>> {
    val userId = authRepository.getCurrentUser().id
    return bookRepository.getBooksByUserId(userId)
  }
}
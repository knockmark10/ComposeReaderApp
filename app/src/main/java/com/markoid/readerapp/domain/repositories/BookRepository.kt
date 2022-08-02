package com.markoid.readerapp.domain.repositories

import com.markoid.readerapp.data.handlers.DataWrapper
import com.markoid.readerapp.domain.entities.Book

interface BookRepository {
  suspend fun getBookInfo(bookId: String): DataWrapper<Book>
  suspend fun searchBooks(query: String): DataWrapper<List<Book>>
  suspend fun saveBook(book: Book): DataWrapper<Boolean>
  suspend fun getBooksByUserId(userId: String): DataWrapper<List<Book>>
}
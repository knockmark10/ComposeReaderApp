package com.markoid.readerapp.data.datasources

import com.markoid.readerapp.data.entities.BookItem
import com.markoid.readerapp.data.entities.BookResponse
import com.markoid.readerapp.data.handlers.DataWrapper
import com.markoid.readerapp.domain.entities.Book

interface BookDataSource {
  suspend fun searchBook(query: String): DataWrapper<BookResponse>
  suspend fun getBookInfo(bookId: String): DataWrapper<BookItem>
  suspend fun saveBook(book: Book): DataWrapper<Boolean>
  suspend fun getBooksByUserId(userId: String): DataWrapper<List<Book>>
}
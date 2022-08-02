package com.markoid.readerapp.data.datasources

import com.markoid.readerapp.data.entities.BookItem
import com.markoid.readerapp.data.entities.BookResponse
import com.markoid.readerapp.data.handlers.ApiHandler
import com.markoid.readerapp.data.handlers.BookRemoteHandler
import com.markoid.readerapp.data.handlers.DataWrapper
import com.markoid.readerapp.data.remote.BookRemoteDatabase
import com.markoid.readerapp.data.remote.BookService
import com.markoid.readerapp.domain.entities.Book
import javax.inject.Inject

class BookDataSourceImpl
@Inject constructor(
  private val bookService: BookService,
  private val apiHandler: ApiHandler,
  private val bookRemoteHandler: BookRemoteHandler,
  private val bookRemoteDatabase: BookRemoteDatabase
) : BookDataSource {

  override suspend fun searchBook(query: String): DataWrapper<BookResponse> {
    val response = bookService.searchBook(query)
    return apiHandler.handleResponse(response)
  }

  override suspend fun getBookInfo(bookId: String): DataWrapper<BookItem> {
    val response = bookService.getBookInfo(bookId)
    return apiHandler.handleResponse(response)
  }

  override suspend fun saveBook(book: Book): DataWrapper<Boolean> = try {
    val result = bookRemoteDatabase.saveBook(book)
    bookRemoteHandler.handleResult(result)
  } catch (exception: Throwable) {
    DataWrapper(exception = exception)
  }

  override suspend fun getBooksByUserId(userId: String): DataWrapper<List<Book>> = try {
    val books = bookRemoteDatabase.getBooksByUserId(userId)
    DataWrapper(data = books)
  } catch (exception: Throwable) {
    DataWrapper(exception = exception)
  }
}
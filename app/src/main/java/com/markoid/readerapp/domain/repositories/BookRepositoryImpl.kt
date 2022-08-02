package com.markoid.readerapp.domain.repositories

import com.markoid.readerapp.data.datasources.BookDataSource
import com.markoid.readerapp.data.handlers.DataWrapper
import com.markoid.readerapp.domain.entities.Book
import com.markoid.readerapp.domain.mappers.BookMapper
import javax.inject.Inject

class BookRepositoryImpl
@Inject constructor(
  private val bookDataSource: BookDataSource,
  private val bookMapper: BookMapper
) : BookRepository {

  override suspend fun getBookInfo(bookId: String): DataWrapper<Book> {
    val bookWrapper = DataWrapper<Book>()
    val dataWrapper = bookDataSource.getBookInfo(bookId)
    dataWrapper.data?.let { bookWrapper.data = bookMapper.bookItemToBook(it) }
    dataWrapper.exception?.let { bookWrapper.exception = it }
    return bookWrapper
  }

  override suspend fun searchBooks(query: String): DataWrapper<List<Book>> {
    val bookWrapper = DataWrapper<List<Book>>()
    val dataWrapper = bookDataSource.searchBook(query)
    dataWrapper.data?.let {
      bookWrapper.data = it.books.map { book -> bookMapper.bookItemToBook(book) }
    }
    dataWrapper.exception?.let { bookWrapper.exception = it }
    return bookWrapper
  }

  override suspend fun saveBook(book: Book): DataWrapper<Boolean> = bookDataSource.saveBook(book)

  override suspend fun getBooksByUserId(
    userId: String
  ): DataWrapper<List<Book>> = bookDataSource.getBooksByUserId(userId)
}
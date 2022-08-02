package com.markoid.readerapp.data.remote

import com.markoid.readerapp.domain.entities.Book

interface BookRemoteDatabase {
  suspend fun saveBook(book: Book): Boolean
  suspend fun getBooksByUserId(userId: String): List<Book>
}
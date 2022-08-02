package com.markoid.readerapp.domain.mappers

import com.markoid.readerapp.data.entities.BookItem
import com.markoid.readerapp.domain.entities.Book
import javax.inject.Inject

class BookMapper @Inject constructor() {

  fun bookItemToBook(bookItem: BookItem): Book = Book(
    id = bookItem.id,
    title = bookItem.bookInfo.title,
    description = bookItem.bookInfo.description,
    author = bookItem.bookInfo.authors,
    notes = bookItem.searchInfo.note,
    coverUrl = bookItem.bookInfo.images.coverUrl,
    numberOfPages = bookItem.bookInfo.pageCount,
    categories = bookItem.bookInfo.categories
  )
}
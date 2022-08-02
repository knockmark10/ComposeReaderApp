package com.markoid.readerapp.data.remote

import com.markoid.readerapp.data.entities.BookItem
import com.markoid.readerapp.data.entities.BookResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BookService {

  @GET("volumes")
  suspend fun searchBook(
    @Query("q") query: String
  ): Response<BookResponse>

  @GET("volumes/{bookId}")
  suspend fun getBookInfo(
    @Path("bookId") bookId: String
  ): Response<BookItem>
}
package com.markoid.readerapp.presentation.modules

import com.markoid.readerapp.data.datasources.BookDataSource
import com.markoid.readerapp.data.datasources.BookDataSourceImpl
import com.markoid.readerapp.data.remote.BookRemoteDatabase
import com.markoid.readerapp.data.remote.BookRemoteDatabaseImpl
import com.markoid.readerapp.data.remote.BookService
import com.markoid.readerapp.domain.repositories.BookRepository
import com.markoid.readerapp.domain.repositories.BookRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class BookModule {

  @Provides
  @Singleton
  fun provideBookService(
    retrofit: Retrofit
  ): BookService = retrofit.create(BookService::class.java)

  @Provides
  @Singleton
  fun provideBookDataSource(
    dataSourceImpl: BookDataSourceImpl
  ): BookDataSource = dataSourceImpl

  @Provides
  @Singleton
  fun provideBookRepository(
    repositoryImpl: BookRepositoryImpl
  ): BookRepository = repositoryImpl

  @Provides
  @Singleton
  fun provideBookRemoteDatabase(
    databaseImpl: BookRemoteDatabaseImpl
  ): BookRemoteDatabase = databaseImpl
}
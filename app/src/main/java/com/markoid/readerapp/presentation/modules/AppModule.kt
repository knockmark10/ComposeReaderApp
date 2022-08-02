package com.markoid.readerapp.presentation.modules

import android.content.Context
import android.content.res.Resources
import com.markoid.readerapp.presentation.dispatchers.CoroutineDispatcherProvider
import com.markoid.readerapp.presentation.dispatchers.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

  @Provides
  @Singleton
  fun providesDispatcherProvider(
    defaultDispatcherProvider: CoroutineDispatcherProvider
  ): DispatcherProvider = defaultDispatcherProvider

  @Provides
  @Singleton
  fun providesResources(
    @ApplicationContext appContext: Context
  ): Resources = appContext.resources
}
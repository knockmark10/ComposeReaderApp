package com.markoid.readerapp.presentation.modules

import android.app.Application
import android.content.res.Resources
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.markoid.readerapp.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val TIMEOUT = 10L

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

  @Provides
  @Singleton
  fun providesGson(): Gson = GsonBuilder().create()

  @Provides
  @Singleton
  fun provideOkHttpCache(application: Application): Cache {
    val cacheSize = 10L * 1024L * 1024L // 10 MiB
    return Cache(application.cacheDir, cacheSize)
  }

  @Provides
  @Singleton
  fun providesOkHttpClient(
    cache: Cache,
  ): OkHttpClient = OkHttpClient.Builder()
    .readTimeout(TIMEOUT, TimeUnit.SECONDS)
    .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
    .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
    .cache(cache)
    .build()

  @Provides
  @Singleton
  fun providesRetrofit(
    gson: Gson,
    okHttpClient: OkHttpClient,
    res: Resources
  ): Retrofit = Retrofit.Builder()
    .baseUrl(res.getString(R.string.book_url))
    .addConverterFactory(GsonConverterFactory.create(gson))
    .client(okHttpClient)
    .build()
}
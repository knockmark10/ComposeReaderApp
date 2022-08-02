package com.markoid.readerapp.presentation.modules

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.markoid.readerapp.data.datasources.AuthDataSource
import com.markoid.readerapp.data.datasources.AuthDataSourceImpl
import com.markoid.readerapp.data.remote.AuthRemoteDatabase
import com.markoid.readerapp.data.remote.AuthRemoteDatabaseImpl
import com.markoid.readerapp.data.remote.AuthService
import com.markoid.readerapp.data.remote.AuthServiceImpl
import com.markoid.readerapp.domain.repositories.AuthRepository
import com.markoid.readerapp.domain.repositories.AuthRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AuthModule {

  @Provides
  @Singleton
  fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

  @Provides
  @Singleton
  fun provideFirebaseFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

  @Provides
  @Singleton
  fun provideAuthService(
    authServiceImpl: AuthServiceImpl
  ): AuthService = authServiceImpl

  @Provides
  @Singleton
  fun provideAuthRemoteDatabase(
    authRemoteDatabase: AuthRemoteDatabaseImpl
  ): AuthRemoteDatabase = authRemoteDatabase

  @Provides
  @Singleton
  fun provideAuthDataSource(
    authDataSourceImpl: AuthDataSourceImpl
  ): AuthDataSource = authDataSourceImpl

  @Provides
  @Singleton
  fun provideAuthRepository(
    authRepositoryImpl: AuthRepositoryImpl
  ): AuthRepository = authRepositoryImpl
}
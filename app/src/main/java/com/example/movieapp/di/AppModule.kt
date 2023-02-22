package com.example.movieapp.di

import com.example.movieapp.data.AuthenticationRepositoryImpl
import com.example.movieapp.domain.AuthenticationRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseAuthInstance(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        auth: FirebaseAuth,
        @Dispatcher.DispatcherIO ioDispatcher: CoroutineDispatcher
    ): AuthenticationRepository {
        return AuthenticationRepositoryImpl(auth, ioDispatcher)
    }
}
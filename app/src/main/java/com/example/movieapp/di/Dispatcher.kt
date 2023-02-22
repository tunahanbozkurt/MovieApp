package com.example.movieapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
object Dispatcher {

    @DispatcherIO
    @Provides
    fun provideDispatcherIO(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @DispatcherDefault
    @Provides
    fun provideDispatcherDefault(): CoroutineDispatcher {
        return Dispatchers.Default
    }

    @DispatcherMain
    @Provides
    fun provideDispatcherMain(): CoroutineDispatcher {
        return Dispatchers.Main
    }

    @DispatcherUnconfined
    @Provides
    fun provideDispatcherUnconfined(): CoroutineDispatcher {
        return Dispatchers.Unconfined
    }

    @Retention(AnnotationRetention.BINARY)
    @Qualifier
    annotation class DispatcherIO

    @Retention(AnnotationRetention.BINARY)
    @Qualifier
    annotation class DispatcherDefault

    @Retention(AnnotationRetention.BINARY)
    @Qualifier
    annotation class DispatcherMain

    @Retention(AnnotationRetention.BINARY)
    @Qualifier
    annotation class DispatcherUnconfined
}
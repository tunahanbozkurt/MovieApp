package com.example.movieapp.di

import android.content.Context
import android.content.SharedPreferences
import com.example.movieapp.data.remote.MovieAPI
import com.example.movieapp.data.remote.dataSource.RemoteMovieDSImpl
import com.example.movieapp.data.remote.repository.AuthenticationRepositoryImpl
import com.example.movieapp.data.remote.repository.MovieRepositoryImpl
import com.example.movieapp.domain.AuthenticationRepository
import com.example.movieapp.domain.MovieRepository
import com.example.movieapp.domain.RemoteMovieDS
import com.example.movieapp.domain.usecase.auth.*
import com.example.movieapp.domain.usecase.field.CheckEmailField
import com.example.movieapp.domain.usecase.field.CheckFieldUseCase
import com.example.movieapp.domain.usecase.field.CheckNameField
import com.example.movieapp.domain.usecase.field.CheckPasswordField
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
    fun provideMovieAPI(): MovieAPI {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteMovieDS(
        api: MovieAPI
    ): RemoteMovieDS {
        return RemoteMovieDSImpl(api = api)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(
        remoteDataSource: RemoteMovieDS,
        @Dispatcher.DispatcherIO ioDispatcher: CoroutineDispatcher
    ): MovieRepository {
        return MovieRepositoryImpl(remoteDataSource, ioDispatcher)
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("com.example.movieapp", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        auth: FirebaseAuth,
        @Dispatcher.DispatcherIO ioDispatcher: CoroutineDispatcher
    ): AuthenticationRepository {
        return AuthenticationRepositoryImpl(auth, ioDispatcher)
    }

    @Provides
    @Singleton
    fun provideCheckFieldUseCase(): CheckFieldUseCase {
        return CheckFieldUseCase(
            checkEmailField = CheckEmailField(),
            checkNameField = CheckNameField(),
            checkPasswordField = CheckPasswordField()
        )
    }

    @Provides
    @Singleton
    fun provideAuthUseCase(authenticationRepository: AuthenticationRepository): AuthUseCase {
        return AuthUseCase(
            createUser = CreateUser(authenticationRepository),
            loginUser = LoginUser(authenticationRepository),
            resetPassword = ResetPassword(authenticationRepository),
            loginWithCredential = LoginWithCredential(authenticationRepository)
        )
    }
}
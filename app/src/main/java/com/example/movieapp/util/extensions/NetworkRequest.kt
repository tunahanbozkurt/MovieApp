package com.example.movieapp.util.extensions

import com.example.movieapp.R
import com.example.movieapp.util.Resource
import com.example.movieapp.util.TaskResult
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

suspend fun <T, R> safeRequestMapper(
    dispatcher: CoroutineDispatcher,
    execute: suspend () -> Response<T>,
    mapper: (T) -> R,
): Resource<R> {
    return withContext(dispatcher) {
        try {
            val response = execute.invoke()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                return@withContext Resource.Success(mapper.invoke(body))
            }
            throw HttpException(response)

        } catch (e: HttpException) {
            e.printStackTrace()
            return@withContext Resource.Error(e.message.toString())

        } catch (e: IOException) {
            e.printStackTrace()
            return@withContext Resource.Error(e.message.toString())

        } catch (e: Exception) {
            e.printStackTrace()
            return@withContext Resource.Error(e.message.toString())
        }
    }
}

suspend fun <T> safeRequest(
    dispatcher: CoroutineDispatcher,
    execute: suspend () -> Response<T>
): Resource<T> {
    return withContext(dispatcher) {
        try {
            val response = execute.invoke()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                return@withContext Resource.Success(body)
            }
            throw HttpException(response)

        } catch (e: HttpException) {
            e.printStackTrace()
            return@withContext Resource.Error(e.message.toString())

        } catch (e: IOException) {
            e.printStackTrace()
            return@withContext Resource.Error(e.message.toString())

        } catch (e: Exception) {
            e.printStackTrace()
            return@withContext Resource.Error(e.message.toString())
        }
    }
}

suspend fun <T> safeFirebaseRequest(
    dispatcher: CoroutineDispatcher,
    execute: suspend () -> Task<T>?
): TaskResult {
    return withContext(dispatcher) {
        try {
            execute.invoke()?.await() ?: throw Exception()
            return@withContext TaskResult.Success

        } catch (e: HttpException) {
            e.printStackTrace()
            return@withContext TaskResult.Error(R.string.network_failure)

        } catch (e: IOException) {
            e.printStackTrace()
            return@withContext TaskResult.Error(R.string.network_failure)

        } catch (e: FirebaseAuthWeakPasswordException) {
            e.printStackTrace()
            return@withContext TaskResult.Error(R.string.weak_password)

        } catch (e: FirebaseAuthInvalidCredentialsException) {
            e.printStackTrace()
            return@withContext TaskResult.Error(R.string.incorrect_email_form)

        } catch (e: FirebaseAuthUserCollisionException) {
            e.printStackTrace()
            return@withContext TaskResult.Error(R.string.user_already_exists)

        } catch (e: FirebaseAuthInvalidUserException) {
            e.printStackTrace()
            return@withContext TaskResult.Error(R.string.invalid_user)
        } catch (e: Exception) {
            e.printStackTrace()
            return@withContext TaskResult.Error(R.string.exception)

        }
    }
}

suspend fun <T> safeFirebaseRequest(
    dispatcher: CoroutineDispatcher,
    execute: suspend () -> Task<T>,
    then: suspend (T) -> Unit
): TaskResult {
    return withContext(dispatcher) {
        try {
            val executeResult = execute.invoke().await()
            then.invoke(executeResult)
            return@withContext TaskResult.Success

        } catch (e: HttpException) {
            e.printStackTrace()
            return@withContext TaskResult.Error(R.string.network_failure)

        } catch (e: IOException) {
            e.printStackTrace()
            return@withContext TaskResult.Error(R.string.network_failure)

        } catch (e: FirebaseAuthWeakPasswordException) {
            e.printStackTrace()
            return@withContext TaskResult.Error(R.string.weak_password)

        } catch (e: FirebaseAuthInvalidCredentialsException) {
            e.printStackTrace()
            return@withContext TaskResult.Error(R.string.incorrect_email_form)

        } catch (e: FirebaseAuthUserCollisionException) {
            e.printStackTrace()
            return@withContext TaskResult.Error(R.string.user_already_exists)

        } catch (e: FirebaseAuthInvalidUserException) {
            e.printStackTrace()
            return@withContext TaskResult.Error(R.string.invalid_user)
        } catch (e: Exception) {
            e.printStackTrace()
            return@withContext TaskResult.Error(R.string.exception)

        }
    }
}

private const val BASE_URL = "https://image.tmdb.org/t/p/w500"
fun createImgUrl(imgPath: String): String {
    return BASE_URL.plus(imgPath)
}
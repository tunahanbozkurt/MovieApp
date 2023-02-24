package com.example.movieapp.util

import android.content.Context
import android.util.Patterns
import android.widget.Toast
import com.example.movieapp.R
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun <T> Context.getDataClassFromJson(
    fileName: String,
    returnType: Class<T>
): T? {
    val jsonInputStream = this.assets.open(fileName)
    val jsonText = jsonInputStream.bufferedReader().use {
        it.readText()
    }
    return Gson().fromJson(jsonText, returnType)
}

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
            throw Exception("safeRequestMapper failure")

        } catch (e: HttpException) {
            return@withContext Resource.Error(e.message.toString())

        } catch (e: IOException) {
            return@withContext Resource.Error(e.message.toString())

        } catch (e: Exception) {
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
            throw Exception("safeRequestMapper failure")

        } catch (e: HttpException) {
            return@withContext Resource.Error(e.message.toString())

        } catch (e: IOException) {
            return@withContext Resource.Error(e.message.toString())

        } catch (e: Exception) {
            return@withContext Resource.Error(e.message.toString())
        }
    }
}

suspend fun <T> safeFirebaseRequest(
    dispatcher: CoroutineDispatcher,
    execute: suspend () -> Task<T>
): TaskResult {
    return withContext(dispatcher) {
        try {
            execute.invoke().await()
            return@withContext TaskResult.Success

        } catch (e: HttpException) {
            e.printStackTrace()
            return@withContext TaskResult.Error(R.string.network_failure)

        } catch (e: IOException) {
            e.printStackTrace()
            return@withContext TaskResult.Error(R.string.network_failure)

        } catch (e: Exception) {
            e.printStackTrace()
            return@withContext TaskResult.Error(R.string.exception)

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
        }
    }
}

private const val BASE_URL = "http://image.tmdb.org/t/p/w500"
fun createImgUrl(imgPath: String): String {
    return BASE_URL.plus(imgPath)
}

fun CharSequence?.isValidEmail() =
    !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun List<Any>.hasError(): Boolean {
    return this.any {
        it is TaskResult.Error || it == true
    }
}

fun TaskResult.isValid(): Boolean {
    return this is TaskResult.Success
}

fun TaskResult.isNotValid(): Boolean {
    return this is TaskResult.Error
}

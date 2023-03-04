package com.example.movieapp.util

import android.content.Context
import android.util.Patterns
import android.widget.Toast
import androidx.compose.runtime.MutableState
import com.example.movieapp.R
import com.example.movieapp.data.remote.dto.detail.Genre
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
import java.net.URLEncoder
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

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

private const val BASE_URL = "https://image.tmdb.org/t/p/w500"
fun createImgUrl(imgPath: String): String {
    return BASE_URL.plus(imgPath)
}

fun String.shortenTitle(): String {
    return if (this.length >= 14) {
        this.take(13).plus("..")
    } else this
}

fun String.encodeToUri(): String {
    return URLEncoder.encode(this, "UTF-8")
}

fun String.convertToDate(): String {
    val inputDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
    val outputDateFormat = SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH)
    val date = inputDateFormat.parse(this)
    val calendar = Calendar.getInstance()
    calendar.time = date ?: return "Unknown"
    return outputDateFormat.format(calendar.time)
}

fun String.uppercaseFirst(): String {
    return this.replaceFirstChar { char ->
        char.uppercase()
    }
}

fun String.addNavArgument(arg: String): String {
    return this.plus("/$arg")
}

fun String.addNavArgument(arg: Int): String {
    return this.plus("/$arg")
}

fun CharSequence?.isValidEmail() =
    !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun List<Any>.hasError(): Boolean {
    return this.any {
        it is TaskResult.Error || it == true
    }
}

fun String.getYearFromDate(): String {
    return try {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = format.parse(this)
        val calendar = Calendar.getInstance()
        if (date != null) {
            calendar.time = date
            return calendar.get(Calendar.YEAR).toString()
        }
        throw ParseException(this, 0)
    } catch (e: ParseException) {
        this
    }
}

fun List<Genre>.toGenreIdList(): List<Int> {
    val list = mutableListOf<Int>()
    this.forEach { genre ->
        list.add(genre.id)
    }
    return list
}

fun String.createYoutubeTrailUrl(): String {
    return "https://www.youtube.com/watch?v=$this"
}

fun MutableState<Boolean>.reverseTheValue() {
    this.value = !this.value
}

fun MutableState<Boolean>.setTrue() {
    this.value = true
}

fun MutableState<Boolean>.setFalse() {
    this.value = false
}

fun TaskResult.onSuccess(): Boolean {
    return this is TaskResult.Success
}

fun TaskResult.onSuccess(execute: () -> Unit) {
    return execute.invoke()
}

fun TaskResult.onError(): Boolean {
    return this is TaskResult.Error
}

fun TaskResult.onError(execute: () -> Unit) {
    return execute.invoke()
}

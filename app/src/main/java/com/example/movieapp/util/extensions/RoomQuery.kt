package com.example.movieapp.util.extensions

import android.database.sqlite.SQLiteException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.io.IOException


suspend fun <T> safeQuery(
    dispatcher: CoroutineDispatcher,
    execute: suspend () -> T
): T? {
    return withContext(dispatcher) {
        try {
            execute.invoke()
        } catch (e: IOException) {
            null
        } catch (e: SQLiteException) {
            null
        } catch (e: IllegalArgumentException) {
            null
        } catch (e: IllegalStateException) {
            null
        }
    }
}







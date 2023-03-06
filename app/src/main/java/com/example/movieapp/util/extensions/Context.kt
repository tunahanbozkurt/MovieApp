package com.example.movieapp.util.extensions

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import com.google.gson.Gson

fun Context.showToast(@StringRes msgResId: Int) {
    val message = this.getString(msgResId)
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
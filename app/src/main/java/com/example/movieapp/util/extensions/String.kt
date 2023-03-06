package com.example.movieapp.util.extensions

import android.util.Patterns
import java.net.URLEncoder
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun String.createYoutubeTrailUrl(): String {
    return "https://www.youtube.com/watch?v=$this"
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

fun String.shortenTitle(): String {
    return if (this.length >= 14) {
        this.take(13).plus("..")
    } else this
}

fun String.encodeToUri(): String {
    return URLEncoder.encode(this, "UTF-8")
}

fun String.convertToDate(): String {
    try {
        val inputDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        val outputDateFormat = SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH)
        val date = inputDateFormat.parse(this)
        val calendar = Calendar.getInstance()
        calendar.time = date ?: return "Unknown"
        return outputDateFormat.format(calendar.time)
    } catch (e: Exception) {
        e.printStackTrace()
        return "Unknown"
    }
}

fun CharSequence?.isValidEmail() =
    !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
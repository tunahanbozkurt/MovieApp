package com.example.movieapp.util.extensions

import androidx.compose.runtime.MutableState

fun MutableState<Boolean>.reverseTheValue() {
    this.value = !this.value
}

fun MutableState<Boolean>.setTrue() {
    this.value = true
}

fun MutableState<Boolean>.setFalse() {
    this.value = false
}
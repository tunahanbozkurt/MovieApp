package com.example.movieapp.presentation.auth

import android.app.Instrumentation.ActivityResult
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthenticationScreenVM @Inject constructor() : ViewModel() {


    fun signInByUsingGoogle(result: ActivityResult) {

    }
}

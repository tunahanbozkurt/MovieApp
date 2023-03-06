package com.example.movieapp.presentation.home.screen.profile

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.example.movieapp.util.constants.SharedPref
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileScreenVM @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    fun getImageBase64(): String? {
        return sharedPreferences.getString(SharedPref.PROFILE_IMAGE_BASE64, null)
    }
}
package com.example.movieapp.presentation.home.screen.language

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.example.movieapp.util.constants.SharedPref
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ChangeLanguageScreenVM @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    companion object {
        const val DEFAULT_LANGUAGE = "en-US"
    }

    private val _selectedLanguage = MutableStateFlow<String?>(null)
    val selectedLanguage = _selectedLanguage.asStateFlow()

    init {
        getSelectedLanguage()
    }

    private fun getSelectedLanguage() {
        val language = sharedPreferences.getString(SharedPref.SELECTED_LANGUAGE, DEFAULT_LANGUAGE)
            ?: DEFAULT_LANGUAGE
        _selectedLanguage.update { language }
    }

    fun updateSelectedLanguage(languageCode: String) {
        with(sharedPreferences.edit()) {
            putString(SharedPref.SELECTED_LANGUAGE, languageCode)
            apply()
        }
        _selectedLanguage.update { languageCode }
    }

}
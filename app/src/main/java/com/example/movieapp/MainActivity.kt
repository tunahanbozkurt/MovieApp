package com.example.movieapp

import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.*
import com.example.movieapp.presentation.navigation.RootNavigationGraph
import com.example.movieapp.ui.theme.MovieAppTheme
import com.example.movieapp.ui.theme.localColor
import com.example.movieapp.util.constants.SharedPref
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import java.util.*
import javax.inject.Inject


@OptIn(ExperimentalAnimationApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MovieAppTheme {
                RootNavigationGraph(
                    navController = rememberAnimatedNavController(),
                    dontShowOnboarding = dontShowOnboarding(),
                    isUserExist = isUserExist(),
                    modifier = Modifier
                        .background(MaterialTheme.localColor.primaryDark)
                        .fillMaxSize()
                )
            }
        }
    }

    private fun setLanguage() {
        val language = sharedPreferences.getString(SharedPref.SELECTED_LANGUAGE, "en-US") ?: "en-US"
        val languageToLoad = if (language == "en-US") "en" else "tr"
        val locale = Locale(languageToLoad)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        baseContext.resources.updateConfiguration(
            config,
            baseContext.resources.displayMetrics
        )
    }

    private fun dontShowOnboarding() =
        sharedPreferences.getBoolean(SharedPref.DONT_SHOW_ONBOARDING, false)

    private fun isUserExist() = firebaseAuth.currentUser != null
}
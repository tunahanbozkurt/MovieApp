package com.example.movieapp

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import com.example.movieapp.presentation.navigation.RootNavigationGraph
import com.example.movieapp.ui.theme.MovieAppTheme
import com.example.movieapp.ui.theme.localColor
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
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
                val systemUiController = rememberSystemUiController()
                DisposableEffect(systemUiController) {
                    onDispose {}
                }

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

    private fun dontShowOnboarding() = sharedPreferences.getBoolean("DONT_SHOW_ONBOARDING", false)
    private fun isUserExist() = firebaseAuth.currentUser != null
}
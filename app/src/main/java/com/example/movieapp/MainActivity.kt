package com.example.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import com.example.movieapp.presentation.navigation.RootNavigationGraph
import com.example.movieapp.ui.theme.MovieAppTheme
import com.example.movieapp.ui.theme.localColor
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@OptIn(ExperimentalAnimationApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppTheme {
                RootNavigationGraph(
                    navController = rememberAnimatedNavController(),
                    modifier = Modifier
                        .background(MaterialTheme.localColor.primaryDark)
                        .fillMaxSize()
                )
            }
        }
    }
}
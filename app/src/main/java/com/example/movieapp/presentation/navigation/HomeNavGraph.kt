package com.example.movieapp.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.movieapp.presentation.home.screen.HomeScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberAnimatedNavController()
) {
    Scaffold(
        bottomBar = {
            BottomNavigation(navController = navController)
        }
    ) {
        AnimatedNavHost(
            navController = navController,
            startDestination = HomeScreen.Home.route,
            route = Graph.HOME,
            modifier = modifier.padding(it)
        ) {

            composable(HomeScreen.Home.route) {
                HomeScreen()
            }
        }
    }
}

sealed class HomeScreen(val route: String) {
    object Home : HomeScreen(route = "Home")
    object Profile : HomeScreen(route = "Profile")
    object Download : HomeScreen(route = "Download")
    object Search : HomeScreen(route = "Search")
}
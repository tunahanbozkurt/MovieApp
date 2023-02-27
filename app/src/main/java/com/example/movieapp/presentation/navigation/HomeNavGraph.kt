package com.example.movieapp.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.movieapp.presentation.home.screen.home.HomeScreen
import com.example.movieapp.presentation.home.screen.search.SearchScreen
import com.example.movieapp.presentation.home.screen.search_result.SearchResultScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberAnimatedNavController()
) {

    val isBottomBarVisible = remember {
        mutableStateOf(true)
    }

    val tabState = remember {
        mutableStateOf("Home")
    }

    DisposableEffect(Unit) {
        val listener =
            NavController.OnDestinationChangedListener { controller, destination, arguments ->
                isBottomBarVisible.value = when (destination.route) {
                    HomeScreen.SearchResult.route -> false
                    else -> {
                        tabState.value = destination.route ?: "Home"
                        true
                    }
                }
            }
        navController.addOnDestinationChangedListener(listener)

        onDispose {
            navController.removeOnDestinationChangedListener(listener)
        }
    }

    Scaffold(
        topBar = {

        },
        bottomBar = {
            if (isBottomBarVisible.value) {
                BottomNavigation(navController = navController, tabState.value)
            }
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

            composable(HomeScreen.SearchResult.route) {
                SearchResultScreen()
            }

            composable(HomeScreen.Search.route) {
                SearchScreen { route ->
                    navController.navigate(route)
                }
            }
        }
    }
}

sealed class HomeScreen(val route: String) {
    object Home : HomeScreen(route = "Home")
    object Profile : HomeScreen(route = "Profile")
    object Download : HomeScreen(route = "Download")
    object Search : HomeScreen(route = "Search")
    object SearchResult : HomeScreen(route = "Search Result")
}
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
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.movieapp.presentation.common.TopApplicationBar
import com.example.movieapp.presentation.home.screen.detail.DetailScreen
import com.example.movieapp.presentation.home.screen.home.HomeScreen
import com.example.movieapp.presentation.home.screen.popular.MostPopularMoviesScreen
import com.example.movieapp.presentation.home.screen.profile.ProfileScreen
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

    val currentDestination = remember {
        mutableStateOf(HomeScreen.Home.route)
    }

    DisposableEffect(Unit) {
        val listener =
            NavController.OnDestinationChangedListener { controller, destination, arguments ->
                when (destination.route) {
                    HomeScreen.Home.route -> {
                        tabState.value = HomeScreen.Home.route
                    }
                    HomeScreen.Search.route -> {
                        tabState.value = HomeScreen.Search.route
                    }
                    HomeScreen.Download.route -> {
                        tabState.value = HomeScreen.Download.route
                    }
                    HomeScreen.Profile.route -> {
                        tabState.value = HomeScreen.Profile.route
                    }
                }
                destination.route?.let {
                    currentDestination.value = it
                }

                isBottomBarVisible.value = when (destination.route) {
                    HomeScreen.SearchResult.route -> false
                    HomeScreen.MostPopularMovies.route -> false
                    else -> {
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
            if (currentDestination.value == HomeScreen.MostPopularMovies.route) {
                TopApplicationBar(
                    title = HomeScreen.MostPopularMovies.route,
                    isBackButtonVisible = true
                ) {
                    navController.popBackStack()
                }
            }
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
                HomeScreen { route ->
                    navController.navigate(route)
                }
            }

            composable(
                HomeScreen.SearchResult.route.plus("?query={query}}"),
                arguments = listOf(navArgument("query") {
                    type = NavType.StringType
                    defaultValue = ""
                })
            ) { backStackEntry ->
                SearchResultScreen(query = backStackEntry.arguments?.getString("query") ?: "") {
                    navController.popBackStack()
                }
            }

            composable(HomeScreen.Search.route) {
                SearchScreen { route ->
                    navController.navigate(route)
                }
            }

            composable(HomeScreen.MostPopularMovies.route) {
                MostPopularMoviesScreen()
            }

            composable(
                HomeScreen.Detail.route.plus("/{id}"),
                arguments = listOf(navArgument("id") { type = NavType.IntType })
            ) { backStackEntry ->
                DetailScreen(movieId = backStackEntry.arguments?.getInt("id") ?: 0)
            }

            composable(HomeScreen.Profile.route) {
                ProfileScreen()
            }
        }
    }
}

sealed class HomeScreen(val route: String) {
    object Home : HomeScreen(route = "Home")
    object Profile : HomeScreen(route = "Profile")
    object Download : HomeScreen(route = "Download")
    object Search : HomeScreen(route = "Search")
    object Detail : HomeScreen(route = "Detail")
    object SearchResult : HomeScreen(route = "Search_Result")
    object MostPopularMovies : HomeScreen("Most Popular Movie")
}
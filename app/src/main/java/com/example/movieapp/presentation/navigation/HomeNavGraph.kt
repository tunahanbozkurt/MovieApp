package com.example.movieapp.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.*
import com.example.movieapp.presentation.common.TopApplicationBar
import com.example.movieapp.presentation.home.screen.detail.DetailScreen
import com.example.movieapp.presentation.home.screen.home.HomeScreen
import com.example.movieapp.presentation.home.screen.popular.MostPopularMoviesScreen
import com.example.movieapp.presentation.home.screen.profile.ProfileScreen
import com.example.movieapp.presentation.home.screen.search.SearchScreen
import com.example.movieapp.presentation.home.screen.search_result.SearchResultScreen
import com.example.movieapp.util.addNavArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeNavGraph(
    modifier: Modifier = Modifier,
    rootNavController: NavHostController,
    navController: NavHostController = rememberAnimatedNavController()
) {

    val isBottomBarVisible = remember {
        mutableStateOf(true)
    }

    val tabState = remember {
        mutableStateOf(HomeScreen.Home.route)
    }

    val currentDestination = remember {
        mutableStateOf(HomeScreen.Home.route)
    }

    DisposableEffect(Unit) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            setTabState(tabState, destination)
            setCurrentDestination(currentDestination, destination)
            setBottomBarVisibility(isBottomBarVisible, destination)
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
                    isBackButtonVisible = true,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    navController.popBackStack()
                }
            }
        },
        bottomBar = {
            if (isBottomBarVisible.value) {
                BottomNavigation(navController = navController, tabState, currentDestination)
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
                SearchResultScreen(
                    query = backStackEntry.arguments?.getString("query") ?: "",
                    onCancel = {
                        navController.popBackStack()

                    },
                    navigate = {
                        navController.navigate(HomeScreen.Detail.route.addNavArgument(it))
                    })
            }

            composable(HomeScreen.Search.route) {
                SearchScreen { route ->
                    navController.navigate(route)
                }
            }

            composable(HomeScreen.MostPopularMovies.route) {
                MostPopularMoviesScreen { route ->
                    navController.navigate(route)
                }
            }

            composable(
                HomeScreen.Detail.route.plus("/{id}"),
                arguments = listOf(navArgument("id") { type = NavType.IntType })
            ) { backStackEntry ->
                DetailScreen(movieId = backStackEntry.arguments?.getInt("id") ?: 0) {
                    navController.popBackStack()
                }
            }

            composable(HomeScreen.Profile.route) {
                ProfileScreen { route ->
                    if (route == Graph.AUTHENTICATION) {
                        rootNavController.navigate(route) {
                            popUpTo(Graph.HOME) {
                                inclusive = true
                            }
                        }
                    }
                }
            }
        }
    }
}

private fun setBottomBarVisibility(state: MutableState<Boolean>, destination: NavDestination) {
    state.value = when (destination.route) {
        HomeScreen.SearchResult.route -> false
        HomeScreen.MostPopularMovies.route -> false
        HomeScreen.Detail.route.plus("/{id}") -> false
        else -> {
            true
        }
    }
}

private fun setCurrentDestination(
    currentDestination: MutableState<String>,
    destination: NavDestination
) {
    destination.route?.let {
        currentDestination.value = it
    }
}

private fun setTabState(tabState: MutableState<String>, destination: NavDestination) {
    when (destination.route) {
        HomeScreen.Home.route -> {
            tabState.value = HomeScreen.Home.route
        }
        HomeScreen.Search.route -> {
            tabState.value = HomeScreen.Search.route
        }
        HomeScreen.Wishlist.route -> {
            tabState.value = HomeScreen.Wishlist.route
        }
        HomeScreen.Profile.route -> {
            tabState.value = HomeScreen.Profile.route
        }
    }
}

sealed class HomeScreen(val route: String) {
    object Home : HomeScreen(route = "Home")
    object Profile : HomeScreen(route = "Profile")
    object Wishlist : HomeScreen(route = "Wishlist")
    object Search : HomeScreen(route = "Search")
    object Detail : HomeScreen(route = "Detail")
    object SearchResult : HomeScreen(route = "Search_Result")
    object MostPopularMovies : HomeScreen("Most Popular Movie")
}
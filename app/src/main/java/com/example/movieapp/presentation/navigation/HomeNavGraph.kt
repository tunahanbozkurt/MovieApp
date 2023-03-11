package com.example.movieapp.presentation.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.*
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.movieapp.R
import com.example.movieapp.presentation.common.component.TopApplicationBar
import com.example.movieapp.presentation.home.screen.PrivacyPolicyScreen
import com.example.movieapp.presentation.home.screen.detail.DetailScreen
import com.example.movieapp.presentation.home.screen.editprofile.EditProfileScreen
import com.example.movieapp.presentation.home.screen.home.HomeScreen
import com.example.movieapp.presentation.home.screen.language.ChangeLanguageScreen
import com.example.movieapp.presentation.home.screen.notification.NotificationScreen
import com.example.movieapp.presentation.home.screen.popular.MostPopularMoviesScreen
import com.example.movieapp.presentation.home.screen.profile.ProfileScreen
import com.example.movieapp.presentation.home.screen.recommended.RecommendedMoviesScreen
import com.example.movieapp.presentation.home.screen.search.SearchScreen
import com.example.movieapp.presentation.home.screen.search.SearchScreenVM
import com.example.movieapp.presentation.home.screen.search_result.SearchResultScreen
import com.example.movieapp.presentation.home.screen.toprated.TopRatedMoviesScreen
import com.example.movieapp.presentation.home.screen.trailer.TrailerScreen
import com.example.movieapp.presentation.home.screen.wish.WishScreen
import com.example.movieapp.util.extensions.addNavArgument
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
        val listener = NavController.OnDestinationChangedListener { controller, destination, _ ->
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
            val isPopularScreen = currentDestination.value == HomeScreen.MostPopularMovies.route
            val isTopRatedScreen = currentDestination.value == HomeScreen.TopRatedMovies.route
            val isRecommendedScreen =
                currentDestination.value == HomeScreen.RecommendedMovies.route.plus("/{id}")
            val isPrivacyPolicyScreen = currentDestination.value == HomeScreen.PrivacyPolicy.route
            val isEditProfileScreen = currentDestination.value == HomeScreen.EditProfile.route
            val isNotificationScreen = currentDestination.value == HomeScreen.Notification.route
            val isLanguageChangeScreen = currentDestination.value == HomeScreen.ChangeLanguage.route
            if (isPopularScreen ||
                isRecommendedScreen ||
                isPrivacyPolicyScreen ||
                isEditProfileScreen ||
                isTopRatedScreen ||
                isNotificationScreen ||
                isLanguageChangeScreen
            ) {
                val title = makeTitle(route = currentDestination.value)
                TopApplicationBar(
                    title = title,
                    isBackButtonVisible = true,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    navController.popBackStack()
                }
            }
        },
        bottomBar = {
            AnimatedVisibility(visible = isBottomBarVisible.value) {
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
                    navigate = { id, type ->
                        navController.navigate(
                            HomeScreen.Detail.route.addNavArgument(id).addNavArgument(type)
                        )
                    })
            }

            composable(HomeScreen.Search.route) {
                SearchScreen(
                    onBackPressed = {
                        navController.apply {
                            navController.navigate(HomeScreen.Home.route) {
                                launchSingleTop = true
                                restoreState = true
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                            }
                        }
                    }
                ) { route ->
                    navController.navigate(route)
                }
            }

            composable(HomeScreen.MostPopularMovies.route) {
                MostPopularMoviesScreen { route ->
                    navController.navigate(route)
                }
            }

            composable(HomeScreen.TopRatedMovies.route) {
                TopRatedMoviesScreen { route ->
                    navController.navigate(route)
                }
            }

            composable(
                HomeScreen.Detail.route.plus("/{id}/{type}"),
                arguments = listOf(
                    navArgument("id") { type = NavType.IntType },
                    navArgument("type") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                DetailScreen(
                    movieId = backStackEntry.arguments?.getInt("id") ?: 0,
                    type = backStackEntry.arguments?.getString("type") ?: "movie"
                ) { route ->
                    if (route.isEmpty()) {
                        navController.popBackStack()
                    } else {
                        navController.navigate(route)
                    }
                }
            }

            composable(HomeScreen.Profile.route) {
                ProfileScreen(
                    onBackPressed = {
                        navController.navigate(HomeScreen.Home.route) {
                            launchSingleTop = true
                            restoreState = true
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                        }
                    }
                ) { route ->
                    if (route == Graph.AUTHENTICATION) {
                        rootNavController.navigate(route) {
                            popUpTo(Graph.HOME) {
                                inclusive = true
                            }
                        }
                    } else {
                        navController.navigate(route)
                    }
                }
            }

            composable(
                HomeScreen.RecommendedMovies.route.plus("/{id}"),
                arguments = listOf(navArgument("id") { type = NavType.IntType })
            ) { backStackEntry ->
                RecommendedMoviesScreen(
                    movieId = backStackEntry.arguments?.getInt("id")
                        ?: SearchScreenVM.BASE_MOVIE_ID,
                    navigate = { route ->
                        navController.navigate(route)
                    }
                )
            }

            composable(HomeScreen.PrivacyPolicy.route) {
                PrivacyPolicyScreen()
            }

            composable(HomeScreen.Wishlist.route) {
                WishScreen(
                    onBackPressed = {
                        navController.navigate(HomeScreen.Home.route) {
                            launchSingleTop = true
                            restoreState = true
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                        }
                    }
                ) { id, type ->
                    navController.navigate(
                        HomeScreen.Detail.route.addNavArgument(id).addNavArgument(type)
                    )
                }
            }

            composable(HomeScreen.EditProfile.route) {
                EditProfileScreen()
            }

            composable(
                HomeScreen.Trailer.route.plus("/{id}/{type}"),
                arguments = listOf(
                    navArgument("id") { type = NavType.IntType },
                    navArgument("type") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                TrailerScreen(
                    id = backStackEntry.arguments?.getInt("id") ?: 0,
                    type = backStackEntry.arguments?.getString("type") ?: "movie"
                )
            }

            composable(HomeScreen.Notification.route) {
                NotificationScreen()
            }

            composable(HomeScreen.ChangeLanguage.route) {
                ChangeLanguageScreen()
            }
        }
    }
}

private fun setBottomBarVisibility(state: MutableState<Boolean>, destination: NavDestination) {
    state.value = when (destination.route) {
        HomeScreen.SearchResult.route.plus("?query={query}}") -> false
        HomeScreen.MostPopularMovies.route -> false
        HomeScreen.TopRatedMovies.route -> false
        HomeScreen.Detail.route.plus("/{id}/{type}") -> false
        HomeScreen.RecommendedMovies.route.plus("/{id}") -> false
        HomeScreen.PrivacyPolicy.route -> false
        HomeScreen.Trailer.route.plus("/{id}/{type}") -> false
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

@Composable
private fun makeTitle(route: String): String {
    return when (route) {
        HomeScreen.Notification.route -> {
            stringResource(id = R.string.notification)
        }
        HomeScreen.MostPopularMovies.route -> {
            stringResource(id = R.string.most_popular_movies)
        }
        HomeScreen.TopRatedMovies.route -> {
            stringResource(id = R.string.toprated_movies)
        }
        HomeScreen.RecommendedMovies.route.plus("/{id}") -> {
            stringResource(id = R.string.recommended_movies)
        }
        HomeScreen.PrivacyPolicy.route -> {
            stringResource(id = R.string.privacy_policy_text)
        }
        HomeScreen.EditProfile.route -> {
            stringResource(id = R.string.edit_profile)
        }
        HomeScreen.ChangeLanguage.route -> {
            stringResource(id = R.string.change_language)
        }
        else -> {
            ""
        }
    }
}

sealed class HomeScreen(val route: String) {
    object Home : HomeScreen(route = "Home")
    object Profile : HomeScreen(route = "Profile")
    object Wishlist : HomeScreen(route = "Wishlist")
    object Search : HomeScreen(route = "Search")
    object Detail : HomeScreen(route = "Detail")
    object Notification :
        HomeScreen(route = "Notification")

    object SearchResult : HomeScreen(route = "Search_Result")
    object MostPopularMovies :
        HomeScreen("Most Popular Movies")

    object TopRatedMovies :
        HomeScreen("Top Rated Movies")

    object RecommendedMovies :
        HomeScreen("Recommended Movies")

    object PrivacyPolicy :
        HomeScreen("Privacy Policy")

    object EditProfile :
        HomeScreen("Edit Profile")

    object ChangeLanguage :
        HomeScreen("Change Language")

    object Trailer : HomeScreen("Trailer")
}
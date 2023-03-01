package com.example.movieapp.presentation.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import com.example.movieapp.presentation.auth.base.AuthenticationScreen
import com.example.movieapp.presentation.auth.login.LoginScreen
import com.example.movieapp.presentation.auth.reset.ResetPasswordScreen
import com.example.movieapp.presentation.auth.signup.SignUpScreen
import com.example.movieapp.presentation.common.TopApplicationBar
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AuthenticationNavGraph(
    modifier: Modifier,
    rootNavController: NavHostController,
    navController: NavHostController = rememberAnimatedNavController()
) {
    val topBarInfo = remember { mutableStateOf(TopBarInfo()) }

    DisposableEffect(Unit) {
        val listener =
            NavController.OnDestinationChangedListener { _, destination, _ ->
                topBarInfo.value = topBarInfo.value.copy(
                    title = makeTitle(destination),
                    isVisible = (destination.route != AuthenticationScreen.Authentication.route)
                )
            }
        navController.addOnDestinationChangedListener(listener)
        onDispose {
            navController.removeOnDestinationChangedListener(listener)
        }
    }

    Scaffold(
        topBar = {
            if (topBarInfo.value.isVisible) {
                TopApplicationBar(
                    title = topBarInfo.value.title,
                    isBackButtonVisible = true,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    navController.popBackStack()
                }
            }

        },
        modifier = modifier
    ) {
        AnimatedNavHost(
            navController = navController,
            startDestination = AuthenticationScreen.Authentication.route,
            route = Graph.AUTHENTICATION,
            modifier = modifier.padding(it)
        ) {
            composable(
                route = AuthenticationScreen.Authentication.route,
                enterTransition = {
                    fadeIn(
                        animationSpec = tween(700)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                },
                popEnterTransition = {
                    fadeIn(
                        animationSpec = tween(700)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(700)
                    )
                }
            ) {
                AuthenticationScreen { route ->
                    when (route) {
                        AuthenticationScreen.SignUp.route -> {
                            navController.navigate(route)
                        }
                        AuthenticationScreen.Login.route -> {
                            navController.navigate(route)
                        }
                        else -> {
                            rootNavController.navigate(route = route) {
                                popUpTo(Graph.AUTHENTICATION) {
                                    inclusive = true
                                }
                            }
                        }
                    }
                }
            }

            composable(
                AuthenticationScreen.SignUp.route,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                },
                popEnterTransition = {
                    slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(700)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(700)
                    )
                }) {
                SignUpScreen { route ->
                    rootNavController.navigate(route) {
                        popUpTo(Graph.AUTHENTICATION) {
                            inclusive = true
                        }
                    }
                }
            }

            composable(
                AuthenticationScreen.Login.route,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                },
                popEnterTransition = {
                    slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(700)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(700)
                    )
                }) {
                LoginScreen { route ->
                    when (route) {
                        Graph.HOME -> {
                            rootNavController.navigate(route) {
                                popUpTo(Graph.AUTHENTICATION) {
                                    inclusive = true
                                }
                            }
                        }
                        AuthenticationScreen.ResetPassword.route -> {
                            navController.navigate(route)
                        }
                    }
                }
            }

            composable(AuthenticationScreen.ResetPassword.route) {
                ResetPasswordScreen {
                    /*TODO*/
                }
            }
        }
    }
}

private fun makeTitle(destination: NavDestination): String {
    return if (destination.route == AuthenticationScreen.Login.route ||
        destination.route == AuthenticationScreen.SignUp.route
    ) destination.route ?: "" else ""
}

sealed class AuthenticationScreen(val route: String) {
    object Authentication : AuthenticationScreen(route = "AUTHENTICATION")
    object Login : AuthenticationScreen(route = "Login")
    object SignUp : AuthenticationScreen(route = "Sign Up")
    object ResetPassword : AuthenticationScreen("RESET_PASSWORD")
}

data class TopBarInfo(
    val title: String = "",
    val isVisible: Boolean = false,
)
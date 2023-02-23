package com.example.movieapp.presentation.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.example.movieapp.presentation.auth.base.AuthenticationScreen
import com.example.movieapp.presentation.auth.login.LoginScreen
import com.example.movieapp.presentation.auth.reset.ResetPasswordScreen
import com.example.movieapp.presentation.auth.signup.SignUpScreen
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.authenticationNavGraph(navController: NavController) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthenticationScreen.Authentication.route
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
                navController.navigate(route = route)
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
            SignUpScreen()
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
                navController.navigate(route)
            }
        }

        composable(AuthenticationScreen.ResetPassword.route) {
            ResetPasswordScreen {
                /*TODO*/
            }
        }
    }
}

sealed class AuthenticationScreen(val route: String) {
    object Authentication : AuthenticationScreen(route = "AUTHENTICATION")
    object Login : AuthenticationScreen(route = "LOGIN")
    object SignUp : AuthenticationScreen(route = "SIGNUP")
    object ResetPassword : AuthenticationScreen("RESET_PASSWORD")
}
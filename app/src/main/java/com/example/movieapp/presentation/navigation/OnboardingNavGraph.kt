package com.example.movieapp.presentation.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.example.movieapp.presentation.onboarding.OnboardingFirstScreen
import com.example.movieapp.presentation.onboarding.OnboardingSecondScreen
import com.example.movieapp.presentation.onboarding.OnboardingThirdScreen
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.onboardingNavGraph(navController: NavHostController) {
    navigation(route = Graph.ONBOARDING, startDestination = OnboardingScreen.First.route) {
        composable(
            route = OnboardingScreen.First.route,
            enterTransition = {
                slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
            },
            exitTransition = {
                slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
            },
            popEnterTransition = {
                slideIntoContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(700))
            },
            popExitTransition = {
                slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(700))
            }
        ) {
            OnboardingFirstScreen() {
                navController.navigate(OnboardingScreen.Second.route)
            }
        }
        composable(route = OnboardingScreen.Second.route) {
            OnboardingSecondScreen {
                navController.navigate(OnboardingScreen.Third.route)
            }
        }
        composable(route = OnboardingScreen.Third.route) {
            OnboardingThirdScreen()
        }
    }
}

sealed class OnboardingScreen(val route: String) {
    object First : OnboardingScreen(route = "FIRST")
    object Second : OnboardingScreen(route = "SECOND")
    object Third : OnboardingScreen(route = "THIRD")
}
package com.example.movieapp.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.example.movieapp.presentation.onboarding.OnboardingScreen
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.onboardingNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.ONBOARDING,
        startDestination = OnboardingScreen.Onboarding.route
    ) {

        composable(
            route = OnboardingScreen.Onboarding.route
        ) {
            OnboardingScreen {
                navController.navigate(Graph.AUTHENTICATION) {
                    popUpTo(Graph.ONBOARDING) {
                        inclusive = true
                    }
                }
            }
        }
    }
}

sealed class OnboardingScreen(val route: String) {
    object Onboarding : OnboardingScreen(route = "FIRST")
}
package com.example.movieapp.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.movieapp.presentation.splash_screen.SplashScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RootNavigationGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    AnimatedNavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.SPLASH,
        modifier = modifier
    ) {

        onboardingNavGraph(navController = navController)
        authenticationNavGraph(navController = navController)

        composable(Graph.SPLASH) {
            SplashScreen {
                navController.navigate(Graph.ONBOARDING) {
                    popUpTo(Graph.SPLASH) {
                        inclusive = true
                    }
                }
            }
        }

    }
}

object Graph {
    const val ROOT = "root_graph"
    const val SPLASH = "splash_screen"
    const val ONBOARDING = "onboarding_graph"
    const val AUTHENTICATION = "authentication_graph"
    const val HOME = "home_graph"
}
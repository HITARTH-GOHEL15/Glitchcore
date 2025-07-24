package com.example.hd_1.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.hd_1.MainActivity
import com.example.hd_1.user_interface.HomeScreen
import com.example.hd_1.user_interface.auth.components.ForgotPasswordScreen
import com.example.hd_1.user_interface.auth.login.LoginScreen
import com.example.hd_1.user_interface.auth.logup.LogUpScreen
import com.example.hd_1.user_interface.onBoarding.OnboardingScreen
import com.example.hd_1.user_interface.game_detail.GameDetailScreen
import com.example.hd_1.user_interface.game_news_detail.GameNewsDetailScreen
import com.example.hd_1.user_interface.game_news_detail.GameNewsDetailViewModel
import com.example.hd_1.user_interface.main.genres.components.GenreWiseGamesScreen
import com.example.hd_1.user_interface.main.platforms.components.PlatformWiseGamesScreen
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun NavGraph(
    context: MainActivity
) {
    val navController = rememberNavController()
    val currentUser = Firebase.auth.currentUser
    val start = if (currentUser != null) Screens.HomeScreenRoute else Screens.OnboardingScreenRoute

    val articleViewModel = koinViewModel<GameNewsDetailViewModel>()

    NavHost(
        navController = navController,
        startDestination = start,
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Start,
                animationSpec = tween(100)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Start,
                animationSpec = tween(100)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Start,
                animationSpec = tween(100)
            )
        }
    ) {
        composable<Screens.OnboardingScreenRoute> {
            OnboardingScreen(
                navController,
                context = context
            )
        }

            //Home
            composable<Screens.HomeScreenRoute>{
                HomeScreen(
                    navController,
                    navigationToDetails = { gameId ->
                        navController.navigate(Screens.SearchGameDetailScreenRoute(gameId))
                    },
                    navigationToUpcomingDetails = { gameId ->
                        navController.navigate(Screens.SearchGameDetailScreenRoute(gameId))
                    },
                    navigationToPlatformWiseDetails = { platformId ->
                        navController.navigate(Screens.PlatformWiseGameListScreenRoute(platformId))
                    },
                    navigationToGenreDetails = { genreId ->
                        navController.navigate(Screens.GenreWiseGameListScreenRoute(genreId))
                    },
                    navigationToArticleDetails = { article ->
                        articleViewModel.setSelectedArticle(article)
                        navController.navigate(Screens.GameNewsDetailScreenRoute)
                    }
                )
            }
            //login
            composable<Screens.LogInScreenRoute> {
                LoginScreen(
                    navController
                )
            }
            //logup
            composable<Screens.LogUpScreenRoute>{
                LogUpScreen(
                    navController
                )
            }
            //forgot Password
            composable<Screens.ForgotPasswordRoute>{
                ForgotPasswordScreen(
                    navController
                )
            }

            //SearchDetail
            composable<Screens.SearchGameDetailScreenRoute> {  navBackStackEntry ->
                GameDetailScreen(
                    navController,
                    gameId = navBackStackEntry.toRoute<Screens.SearchGameDetailScreenRoute>().gameId
                )
            }

        composable<Screens.PlatformWiseGameListScreenRoute> { navBackStackEntry ->
            PlatformWiseGamesScreen(
                platformId = navBackStackEntry.toRoute<Screens.PlatformWiseGameListScreenRoute>().platformId,
                navigateToDetails = { gameId ->
                    navController.navigate(Screens.SearchGameDetailScreenRoute(gameId))
                },
                navController = navController
            )
        }

        composable<Screens.GenreWiseGameListScreenRoute> { navBackStackEntry ->
            GenreWiseGamesScreen(
                genreId = navBackStackEntry.toRoute<Screens.GenreWiseGameListScreenRoute>().genresId,
                navigateToDetails = { gameId ->
                    navController.navigate(Screens.SearchGameDetailScreenRoute(gameId))
                },
                navController = navController
            )
        }

        composable<Screens.GameNewsDetailScreenRoute> { navBackStackEntry ->
            val article = articleViewModel.selectedArticle.collectAsStateWithLifecycle().value

            if (article != null) {
                GameNewsDetailScreen(
                    article = article,
                    navController = navController
                )
            }
        }
    }
}


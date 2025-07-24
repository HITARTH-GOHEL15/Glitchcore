package com.example.hd_1.navigation

import kotlinx.serialization.Serializable

sealed class Screens(val route: String) {
    //Home
    @Serializable
    object HomeScreenRoute

    //onBoarding
    @Serializable
     object OnboardingScreenRoute

    //UserInfo
    @Serializable
    object UserInfoScreenRoute

    //Auth
    @Serializable
     object LogInScreenRoute

    @Serializable
     object LogUpScreenRoute

    @Serializable
     object ForgotPasswordRoute

    @Serializable
    object AuthRoute

    //Search_gameDetail
    @Serializable
    data class SearchGameDetailScreenRoute(val gameId: Int)

    @Serializable
    data class PlatformWiseGameListScreenRoute(val platformId: Int)

    @Serializable
    data class GenreWiseGameListScreenRoute(val genresId: Int)

    @Serializable
    object GameNewsDetailScreenRoute


}
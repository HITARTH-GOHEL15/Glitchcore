package com.example.hd_1.user_interface

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hd_1.R
import com.example.hd_1.data.gameNewsModel.ArticleModel
import com.example.hd_1.ui.theme.sora_extraBold
import com.example.hd_1.user_interface.search.NewsSearchListScreen
import com.example.hd_1.user_interface.search.SearchListScreen
import org.example.hito3.ui.main.MainScreen

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavController,
    navigationToDetails: (gameId: Int) -> Unit,
    navigationToArticleDetails: (ArticleModel) -> Unit,
    navigationToGenreDetails: (genreId: Int) -> Unit,
    navigationToUpcomingDetails: (gameId: Int) -> Unit,
    navigationToPlatformWiseDetails: (platformId: Int) -> Unit
) {
    var bottomNavigationScreens by rememberSaveable {
        mutableStateOf(BottomNavigationScreens.MainScreenView)
    }

    var isHomeScreen by remember {
        mutableStateOf(true)
    }

    Scaffold(
        topBar = {
            if (isHomeScreen) {
                TopAppBar(
                    title = {
                    },
                    actions = {
                        IconButton(
                            onClick = {}
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Notifications,
                                contentDescription = "Notifications"
                            )
                        }
                        //profile-icon
                        IconButton(
                            onClick = {}
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.AccountCircle,
                                contentDescription = "Profile"
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent
                    )
                )
            }
        },
        bottomBar = {
            NavigationBar(
                containerColor = Color.Transparent
            ) {
                NavigationBarItem(
                    selected = bottomNavigationScreens == BottomNavigationScreens.MainScreenView,
                    onClick = {
                        bottomNavigationScreens = BottomNavigationScreens.MainScreenView
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Home,
                            contentDescription = "Home"
                        )
                    }
                )
                NavigationBarItem(
                    selected = bottomNavigationScreens == BottomNavigationScreens.SearchScreenView,
                    onClick = {
                        bottomNavigationScreens = BottomNavigationScreens.SearchScreenView
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = "Search"
                        )
                    }
                )
                NavigationBarItem(
                    selected = bottomNavigationScreens == BottomNavigationScreens.ChatScreenView,
                    onClick = {
                        bottomNavigationScreens = BottomNavigationScreens.ChatScreenView
                    },
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.chat_bubble_24dp_e8eaed_fill0_wght400_grad0_opsz24),
                            contentDescription = "chat"
                        )
                    }
                )
                NavigationBarItem(
                    selected = bottomNavigationScreens == BottomNavigationScreens.NewsScreenView,
                    onClick = {
                        bottomNavigationScreens = BottomNavigationScreens.NewsScreenView
                    },
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.news_24dp_e3e3e3_fill0_wght400_grad0_opsz24),
                            contentDescription = "news"
                        )
                    }
                )
            }
        }
    ) {
       AnimatedContent(
           targetState = bottomNavigationScreens,
           label = "",
           transitionSpec = {
               when(this.targetState) {
                   BottomNavigationScreens.MainScreenView -> slideInHorizontally(){it}.togetherWith(slideOutHorizontally(){-it})
                   BottomNavigationScreens.SearchScreenView -> slideInHorizontally(){it}.togetherWith(slideOutHorizontally(){-it})
                   BottomNavigationScreens.ChatScreenView -> slideInHorizontally(){it}.togetherWith(slideOutHorizontally(){-it})
                   BottomNavigationScreens.NewsScreenView -> slideInHorizontally(){it}.togetherWith(slideOutHorizontally(){-it})
               }
            },
           modifier = Modifier
               .background(color = MaterialTheme.colorScheme.background)
       ) { navScreen ->
           when(navScreen) {
               BottomNavigationScreens.SearchScreenView -> {
                   isHomeScreen = false
                   SearchListScreen(
                       navigateToDetails = navigationToDetails,
                       modifier = Modifier
                   )
               }
               BottomNavigationScreens.MainScreenView -> {
                   isHomeScreen = true
                   MainScreen(
                       navigateToGenreDetails = navigationToGenreDetails,
                       navigateToUpcomingDetails = navigationToUpcomingDetails,
                       navigateToPlatformGameList = navigationToPlatformWiseDetails
                       )
               }
               BottomNavigationScreens.ChatScreenView -> {
                   isHomeScreen = false
                   Box(
                       modifier = Modifier
                           .fillMaxSize(),
                       contentAlignment = Alignment.Center
                   ) {
                       Text(
                           text = "feature Coming soon",
                           style = MaterialTheme.typography.titleLarge,
                           color = MaterialTheme.colorScheme.onSecondary,
                           fontFamily = sora_extraBold,
                           fontSize = 30.sp,
                           modifier = Modifier
                               .padding(start = 13.dp)
                       )
                   }
               }
               BottomNavigationScreens.NewsScreenView -> {
                   isHomeScreen = false
                   NewsSearchListScreen(
                       navigateToDetails = navigationToArticleDetails
                   )
               }
           }
       }
    }
}

private enum class BottomNavigationScreens{
    SearchScreenView,
    MainScreenView,
    ChatScreenView,
    NewsScreenView
}
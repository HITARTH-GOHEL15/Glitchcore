package com.example.hd_1.user_interface.main.platforms.components

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.hd_1.data.GameModel
import com.example.hd_1.ui.theme.sora_extraBold
import com.example.hd_1.user_interface.auth.components.CircularIndicator
import com.example.hd_1.user_interface.main.platforms.PlatformRowViewModel
import com.example.hd_1.user_interface.search.components.SearchBar
import com.example.hd_1.user_interface.search.components.SearchListCard
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PlatformWiseGamesScreen(
    platformId: Int,
    platformName: String = "Platform Wise Games",
    navController: NavController,
    navigateToDetails: (gameId: Int) -> Unit
) {
    val viewModel = koinViewModel<PlatformRowViewModel>()
    val games by viewModel.PlatformsWiseGames.collectAsStateWithLifecycle()

    var searchQuery by rememberSaveable { mutableStateOf("") }

    var isSearchBar by remember {
        mutableStateOf(true)
    }

    // Fetch games for selected platform
    LaunchedEffect(platformId) {
        viewModel.onPlatformWiseSearchQuery(searchQuery)
        viewModel.loadGamesForPlatform(platformId.toString())
    }



    Scaffold(
        topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = platformName,
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.secondary,
                            fontFamily = sora_extraBold,
                            fontWeight = FontWeight.ExtraBold,
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = { navController.popBackStack() }
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                                contentDescription = "ArrowBack"
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background
                    )
                )
        }
    ) {
        AnimatedContent(games.isNotEmpty()) { loaded ->
            if (loaded) {
                PlatformGamesList(
                    games = games,
                    onGameClick = navigateToDetails
                )
            } else {
                CircularIndicator(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
private fun PlatformGamesList(
    games: List<GameModel>,
    onGameClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 85.dp),
        contentPadding = WindowInsets.safeDrawing.asPaddingValues()
    ) {
        items(
            games,
            key = { it.id.toString() }
        ) { game ->
            SearchListCard(
                game = game,
                onGameClick = {
                    game.id?.let { onGameClick(it) }
                }
            )
        }
    }
}

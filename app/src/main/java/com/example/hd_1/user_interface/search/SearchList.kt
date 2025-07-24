package com.example.hd_1.user_interface.search

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.hd_1.data.GameModel
import com.example.hd_1.user_interface.auth.components.CircularIndicator
import com.example.hd_1.user_interface.search.components.SearchBar
import com.example.hd_1.user_interface.search.components.SearchListCard
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchListScreen(
    modifier: Modifier,
    navigateToDetails: (gameId: Int) -> Unit,
) {
    var searchQuery by rememberSaveable { mutableStateOf("") }

    val viewModel = koinViewModel<SearchListViewModel>()
    val games by viewModel.searchResults.collectAsStateWithLifecycle(
    )

    LaunchedEffect(searchQuery) {
        viewModel.onSearchQuery(searchQuery)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    SearchBar(
                        searchQuery = searchQuery,
                        onSearchQueryChange = {
                            searchQuery = it
                        },
                        modifier = Modifier
                            .padding(bottom = 12.dp)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) {
        AnimatedContent(
            games.isNotEmpty()
        ) { gamesAvailable ->
            if (gamesAvailable) {
                SearchList(
                    games,
                    onGameClick = navigateToDetails
                )
            } else {
                CircularIndicator(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
private fun SearchList(
    games: List<GameModel>,
    onGameClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 85.dp , bottom = 90.dp),
        contentPadding = WindowInsets.safeDrawing.asPaddingValues()
    ) {
        items(
            games,
            key = {it.id.toString()}
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
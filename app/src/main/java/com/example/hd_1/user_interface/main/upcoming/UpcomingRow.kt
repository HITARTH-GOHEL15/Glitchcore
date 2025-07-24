package com.example.hd_1.user_interface.main.upcoming

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.hd_1.data.GameModel
import com.example.hd_1.user_interface.main.upcoming.components.UpcomingGamesViewModel
import com.example.hd_1.user_interface.main.upcoming.components.UpcomingRowCard
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun UpcomingRowScreen(
    modifier: Modifier,
    navigateToDetails: (gameId: Int) -> Unit
) {
    val viewModel = koinViewModel<UpcomingGamesViewModel>()
    val upcomingGames by viewModel.upcomingResult.collectAsStateWithLifecycle()

    AnimatedContent(
        upcomingGames.isNotEmpty()
    ) { upcomingGamesAvailable ->
        if(upcomingGamesAvailable) {
            UpcomingRow(
                upcomingGames = upcomingGames,
                onUpcomingGamesClick = navigateToDetails
            )
        }
    }
}

@Composable
fun UpcomingRow(
    upcomingGames: List<GameModel>,
    onUpcomingGamesClick: (Int) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = androidx.compose.foundation.layout.WindowInsets.safeDrawing.asPaddingValues()
    ) {
        items(
            upcomingGames,
            key = {
                it.id.toString()
            }
        ) { upcomingGames ->
            UpcomingRowCard(
                upcomingGames = upcomingGames,
                onUpcomingGameClick = {
                    upcomingGames.id?.let {
                        onUpcomingGamesClick(it)
                    }
                }
            )
        }
    }
}
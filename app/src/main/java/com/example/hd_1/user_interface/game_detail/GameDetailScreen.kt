package com.example.hd_1.user_interface.game_detail

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.hd_1.ui.theme.sora_extraBold
import org.koin.compose.viewmodel.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDetailScreen(
    navController: NavController,
    gameId: Int
) {
    val viewModel = koinViewModel<GameDetailViewModel>()
    val game by viewModel.getGameDetail(gameId).collectAsStateWithLifecycle(initialValue = null)
    val screenshots by viewModel.getScreenshots(gameId).collectAsStateWithLifecycle(emptyList())
    val trailers by viewModel.getYoutubeTrailers(gameId, game?.name ?: "").collectAsStateWithLifecycle(emptyList())
    val streams by viewModel.getYoutubeStreams(gameId , game?.name ?: "").collectAsStateWithLifecycle(emptyList())


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    game?.name?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.secondary,
                            fontFamily = sora_extraBold,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = {navController.popBackStack()}
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                            contentDescription = "ArrowBack"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        }
    ) {
        AnimatedContent(
            game != null
        ) { gameAvailable ->
            if(gameAvailable) {
                GameDetails(game!! , screenshots , trailers , streams)
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
            }
        }
    }
}
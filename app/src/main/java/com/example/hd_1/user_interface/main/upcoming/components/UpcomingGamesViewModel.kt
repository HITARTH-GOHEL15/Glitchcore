package com.example.hd_1.user_interface.main.upcoming.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hd_1.data.GameModel
import com.example.hd_1.data.GameRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class UpcomingGamesViewModel(
    gameRepository: GameRepository
): ViewModel() {
    val upcomingResult: StateFlow<List<GameModel>> =
        gameRepository.getUpcomingGame()
            .stateIn(viewModelScope , SharingStarted.WhileSubscribed(5000) , emptyList())
}
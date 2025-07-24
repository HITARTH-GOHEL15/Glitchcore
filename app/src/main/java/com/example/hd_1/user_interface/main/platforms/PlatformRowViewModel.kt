package com.example.hd_1.user_interface.main.platforms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hd_1.data.GameModel
import com.example.hd_1.data.GameRepository
import com.example.hd_1.data.rawgModels.PlatformModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PlatformRowViewModel(
    private val gameRepository: GameRepository
) : ViewModel() {
    val PlatformsResults: StateFlow<List<PlatformModel>> =
        gameRepository.getPlatform()
            .stateIn(viewModelScope , SharingStarted.WhileSubscribed(5000) , emptyList())

    val PlatformsWiseGames: StateFlow<List<GameModel>> =
        gameRepository.getPlatformWiseGame()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun loadGamesForPlatform(platformId: String) {
        viewModelScope.launch {
            gameRepository.getPlatformsWiseGames(platformId) // fetch and save to storage
            // no need to assign manually
        }
    }

    // 🔍 Trigger a new search
    fun onPlatformWiseSearchQuery(query: String) {
        viewModelScope.launch {
            gameRepository.searchGames(query)
        }
    }

}
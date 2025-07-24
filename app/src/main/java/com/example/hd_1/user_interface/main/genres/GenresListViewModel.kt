package com.example.hd_1.user_interface.main.genres

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.serialization.generateRouteWithArgs
import com.example.hd_1.data.GameModel
import com.example.hd_1.data.GameRepository
import com.example.hd_1.data.rawgModels.GenreModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class GenresListViewModel (
   private val gameRepository: GameRepository
) : ViewModel() {
    val GenresResults: StateFlow<List<GenreModel>> =
        gameRepository.getGenres()
            .stateIn(viewModelScope , SharingStarted.WhileSubscribed(5000) , emptyList())

    val GenresWiseGames: StateFlow<List<GameModel>> =
        gameRepository.getGenresWiseGame()
            .stateIn(viewModelScope , SharingStarted.WhileSubscribed(5000) , emptyList())

    fun loadGamesForGenre(genreId: String) {
        viewModelScope.launch {
            gameRepository.getGenresWiseGames(genreId)
        }
    }
}
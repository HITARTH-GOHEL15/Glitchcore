package com.example.hd_1.user_interface.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hd_1.data.GameModel
import com.example.hd_1.data.GameRepository
import com.example.hd_1.data.gameNewsModel.ArticleModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SearchListViewModel (
    private val gameRepository: GameRepository
) : ViewModel(){

    val searchResults: StateFlow<List<GameModel>> =
        gameRepository.getSearchResults()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())


    fun onSearchQuery(query: String) {
        viewModelScope.launch {
            gameRepository.searchGames(query)
        }
    }

    val newsSearchResults: StateFlow<List<ArticleModel>> =
        gameRepository.getSearchGameNewsResults()
            .stateIn(viewModelScope , SharingStarted.WhileSubscribed(5000) , emptyList())

    fun onNewsSearchQuery(query: String) {
        viewModelScope.launch {
            gameRepository.searchGameNews(query)
        }
    }
}
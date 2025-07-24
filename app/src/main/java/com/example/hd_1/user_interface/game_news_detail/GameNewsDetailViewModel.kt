package com.example.hd_1.user_interface.game_news_detail

import androidx.lifecycle.ViewModel
import com.example.hd_1.data.GameRepository
import com.example.hd_1.data.gameNewsModel.ArticleModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GameNewsDetailViewModel(
    private val gameRepository: GameRepository
) : ViewModel() {
    private val _selectedArticle = MutableStateFlow<ArticleModel?>(null)
    val selectedArticle: StateFlow<ArticleModel?> = _selectedArticle

    fun setSelectedArticle(article: ArticleModel) {
        _selectedArticle.value = article
    }
}
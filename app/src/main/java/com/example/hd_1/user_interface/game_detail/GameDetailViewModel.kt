package com.example.hd_1.user_interface.game_detail

import androidx.lifecycle.ViewModel
import com.example.hd_1.data.GameModel
import com.example.hd_1.data.GameRepository
import com.example.hd_1.data.rawgModels.ScreenshotModel
import com.example.hd_1.data.streamModel.StreamItemModel
import com.example.hd_1.data.youtubeTrailerModel.ItemModel
import com.example.hd_1.data.youtubeTrailerModel.YoutubeTrailerModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GameDetailViewModel(
    private val gameRepository: GameRepository
) : ViewModel() {
    fun getGameDetail(
        gameId: Int
    ): Flow<GameModel?> = flow {
        gameRepository.fetchAndStoreGameDetail(gameId)
        emitAll(gameRepository.getGameDetail(gameId))
    }.flowOn(Dispatchers.IO)

    fun getScreenshots(gameId: Int): Flow<List<ScreenshotModel>> = flow {
        gameRepository.fetchAndStoreScreenshots(gameId)
        emitAll(gameRepository.getScreenshots(gameId))
    }.flowOn(Dispatchers.IO)

    fun getYoutubeTrailers(gameId: Int, gameName: String): Flow<List<ItemModel>> = flow {
        gameRepository.fetchAndStoreYoutubeTrailers(gameId, gameName)
        emitAll(gameRepository.getYoutubeTrailers(gameId))
    }.flowOn(Dispatchers.IO)

    fun getYoutubeStreams(gameId: Int , gameName: String): Flow<List<StreamItemModel>> = flow {
        gameRepository.fetchAndStoreYoutubeStreams(gameId, gameName)
        emitAll(gameRepository.getYoutubeStreams(gameId))
    }.flowOn(Dispatchers.IO)

}
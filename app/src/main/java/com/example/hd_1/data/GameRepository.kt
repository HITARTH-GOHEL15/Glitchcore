package com.example.hd_1.data

import com.example.hd_1.data.gameNewsModel.ArticleModel
import com.example.hd_1.data.rawgModels.DeveloperModel
import com.example.hd_1.data.rawgModels.GenreModel
import com.example.hd_1.data.rawgModels.PlatformModel
import com.example.hd_1.data.rawgModels.PublisherModel
import com.example.hd_1.data.rawgModels.ScreenshotModel
import com.example.hd_1.data.rawgModels.StoreModelX
import com.example.hd_1.data.rawgModels.TagModel
import com.example.hd_1.data.streamModel.StreamItemModel
import com.example.hd_1.data.youtubeTrailerModel.ItemModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class GameRepository(
    private val gameApi: GameAPI,
    private val gameStorage: GameStorage
) {
    private val scope = CoroutineScope(SupervisorJob())


    fun initialize() {
        scope.launch {
            refreshUpcoming()
            refreshPlatform()
            refreshGenres()
            refreshAll()
            refreshTopRated()
            refreshStores()
            refreshDevelopers()
            refreshPublishers()
            refreshTags()
        }
    }

    private suspend fun refreshUpcoming() {
        gameStorage.saveUpcoming(gameApi.getUpcomingGames())
    }

    private suspend fun refreshPlatform() {
        gameStorage.savePlatform(gameApi.getGamesPlatforms())
    }

    private suspend fun refreshGenres() {
        gameStorage.saveGenres(gameApi.getGamesGenres())
    }

    private suspend fun refreshAll() {
        gameStorage.saveAll(gameApi.getAllGames())
    }

    private suspend fun refreshTopRated() {
        gameStorage.saveTopRated(gameApi.getTopRatedGames())
    }

    private suspend fun refreshStores() {
        gameStorage.saveStores(gameApi.getGamesStores())
    }

    private suspend fun refreshPublishers() {
        gameStorage.savePublishers(gameApi.getGamesPublisher())
    }

    private suspend fun refreshDevelopers() {
        gameStorage.saveDevelopers(gameApi.getGamesDevelopers())
    }

    private suspend fun refreshTags() {
        gameStorage.saveTags(gameApi.getGamesTags())
    }

    /* ------- public search call ------- */
    suspend fun searchGames(query: String) {
        val result = gameApi.getSearchGames(query)         // network
        gameStorage.saveSearchResults(result)            // cache in memory
    }

    suspend fun searchGameNews(query: String) {
        val result = gameApi.searchGamesNews(query)
        gameStorage.saveGameNewsSearchResults(result)
    }

    suspend fun getPlatformsWiseGames(query: String) {
        val result = gameApi.getPlatformWiseGames(query)
        gameStorage.savePlatformWise(result)
    }

    suspend fun getGenresWiseGames(query: String) {
        val result = gameApi.getGenreWiseGames(query)
        gameStorage.saveGenreWise(result)
    }

    suspend fun fetchAndStoreGameDetail(gameId: Int) {
        val game = gameApi.getGameDetailById(gameId)
        game?.let { gameStorage.saveGameDetail(it) }
    }

    suspend fun fetchAndStoreScreenshots(gameId: Int) {
        val result = gameApi.getGameScreenshotById(gameId)
        gameStorage.saveScreenshots(gameId, result)
    }

    suspend fun fetchAndStoreYoutubeTrailers(gameId: Int , gameName: String) {
        val trailers = gameApi.searchGameTrailers(gameName)
        gameStorage.saveYoutubeTrailers(gameId , trailers)
    }

    suspend fun fetchAndStoreYoutubeStreams(gameId: Int , gameName: String) {
        val streams = gameApi.searchGameStreams(gameName)
        gameStorage.saveYoutubeStreams(gameId , streams)
    }

    fun getYoutubeTrailers(gameId: Int): Flow<List<ItemModel>> = gameStorage.getYoutubeTrailers(gameId)

    fun getYoutubeStreams(gameId: Int): Flow<List<StreamItemModel>> = gameStorage.getYoutubeStreams(gameId)

    fun getScreenshots(gameId: Int): Flow<List<ScreenshotModel>> = gameStorage.getScreenshots(gameId)

    fun getGameDetail(gameId: Int): Flow<GameModel?> = gameStorage.getGameDetailById(gameId)

    fun getGameNewsDetail(articleUrl: String): Flow<ArticleModel?> = gameStorage.getGameNewsDetailById(articleUrl)

    fun getUpcomingGame(): Flow<List<GameModel>> = gameStorage.getUpcomingGame()
    fun getGenres(): Flow<List<GenreModel>> = gameStorage.getGenres()
    fun getPlatform(): Flow<List<PlatformModel>> = gameStorage.getPlatform()
    fun getPlatformWiseGame(): Flow<List<GameModel>> = gameStorage.getPlatformWise()
    fun getGenresWiseGame(): Flow<List<GameModel>> = gameStorage.getGenresWise()
    fun getAllGame(): Flow<List<GameModel>> = gameStorage.getAllGame()
    fun getSearchResults(): Flow<List<GameModel>> = gameStorage.getSearchResults()
    fun getTopRatedGame(): Flow<List<GameModel>> = gameStorage.getTopRatedGame()
    fun getStores(): Flow<List<StoreModelX>> = gameStorage.getStores()
    fun getPublishers(): Flow<List<PublisherModel>> = gameStorage.getPublishers()
    fun getDevelopers(): Flow<List<DeveloperModel>> = gameStorage.getDevelopers()
    fun getTags(): Flow<List<TagModel>> = gameStorage.getTags()
    fun getSearchGameNewsResults(): Flow<List<ArticleModel>> = gameStorage.getGameNewsSearchResults()


    fun getUpcomingGameById(GameId: Int): Flow<GameModel?> = gameStorage.getUpcomingWiseGameById(GameId)
    fun getPlatformWiseById(GameId: Int): Flow<GameModel?> = gameStorage.getPlatformWiseGameById(GameId)
    fun getGenresWiseById(GameId: Int): Flow<GameModel?> = gameStorage.getGenresWiseGameById(GameId)
    fun getGameById(GameId: Int): Flow<GameModel?> = gameStorage.getGameById(GameId)
    fun getSearchResultsById(GameId: Int): Flow<GameModel?> = gameStorage.getSearchResultById(GameId)
    fun getTopRatedGameById(GameId: Int): Flow<GameModel?> = gameStorage.getTopRatedGameByTd(GameId)
    fun getStoresById(GameId: Int): Flow<StoreModelX?> = gameStorage.getStoreGameById(GameId)
    fun getPublishersById(GameId: Int): Flow<PublisherModel?> = gameStorage.getPublisherGameById(GameId)
    fun getDevelopersById(GameId: Int): Flow<DeveloperModel?> = gameStorage.getDeveloperGameById(GameId)
    fun getTagsById(GameId: Int): Flow<TagModel?> = gameStorage.getTagGameById(GameId)
}

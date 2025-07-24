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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

interface GameStorage {
    suspend fun saveGenres(newObjects: List<GenreModel>)
    suspend fun savePlatform(newObjects: List<PlatformModel>)
    suspend fun saveUpcoming(newObjects: List<GameModel>)
    suspend fun savePlatformWise(newObjects: List<GameModel>)
    suspend fun saveGenreWise(newObjects: List<GameModel>)
    suspend fun saveAll(newObjects: List<GameModel>)
    suspend fun saveSearchResults(newObjects: List<GameModel>)
    suspend fun saveTopRated(newObjects: List<GameModel>)
    suspend fun saveStores(newObjects: List<StoreModelX>)
    suspend fun saveDevelopers(newObjects: List<DeveloperModel>)
    suspend fun savePublishers(newObjects: List<PublisherModel>)
    suspend fun saveTags(newObjects: List<TagModel>)
    suspend fun saveGameDetail(game: GameModel)
    suspend fun saveScreenshots(gameId: Int, newObjects: List<ScreenshotModel>)
    suspend fun saveYoutubeTrailers(gameId: Int , trailers: List<ItemModel>)
    suspend fun saveYoutubeStreams(gameId: Int  , streams: List<StreamItemModel>)
    suspend fun saveGameNewsSearchResults(newObjects: List<ArticleModel>)


    fun getUpcomingWiseGameById(GameId : Int): Flow<GameModel?>
    fun getPlatformWiseGameById(GameId: Int): Flow<GameModel?>
    fun getGenresWiseGameById(GameId: Int): Flow<GameModel?>
    fun getGameById(GameId: Int): Flow<GameModel?>
    fun getSearchResultById(GameId: Int): Flow<GameModel?>
    fun getTopRatedGameByTd(GameId: Int): Flow<GameModel?>
    fun getStoreGameById(GameId: Int): Flow<StoreModelX?>
    fun getDeveloperGameById(GameId: Int): Flow<DeveloperModel?>
    fun getPublisherGameById(GameId: Int): Flow<PublisherModel?>
    fun getTagGameById(GameId: Int): Flow<TagModel?>
    fun getGameDetailById(gameId: Int): Flow<GameModel?>
    fun getGameNewsDetailById(articleUrl: String): Flow<ArticleModel?>


    fun getUpcomingGame() : Flow<List<GameModel>>
    fun getSearchResults(): Flow<List<GameModel>>
    fun getGenres() : Flow<List<GenreModel>>
    fun getPlatform() : Flow<List<PlatformModel>>
    fun getPlatformWise() : Flow<List<GameModel>>
    fun getGenresWise() : Flow<List<GameModel>>
    fun getAllGame() : Flow<List<GameModel>>
    fun getTopRatedGame() : Flow<List<GameModel>>
    fun getStores() : Flow<List<StoreModelX>>
    fun getDevelopers() : Flow<List<DeveloperModel>>
    fun getPublishers() : Flow<List<PublisherModel>>
    fun getTags() : Flow<List<TagModel>>
    fun getScreenshots(gameId: Int) : Flow<List<ScreenshotModel>>
    fun getYoutubeTrailers(gameId: Int): Flow<List<ItemModel>>
    fun getYoutubeStreams(gameId: Int): Flow<List<StreamItemModel>>
    fun getGameNewsSearchResults(): Flow<List<ArticleModel>>

}

class InMemoryGameStorage : GameStorage {

    private val upcomingGames = MutableStateFlow(emptyList<GameModel>())
    private val Genres = MutableStateFlow(emptyList<GenreModel>())
    private val Platform = MutableStateFlow(emptyList<PlatformModel>())
    private val GenresWiseGames = MutableStateFlow(emptyList<GameModel>())
    private val PlatformWiseGames = MutableStateFlow(emptyList<GameModel>())
    private val AllGames = MutableStateFlow(emptyList<GameModel>())
    private val GetSearchGames = MutableStateFlow(emptyList<GameModel>())
    private val searchResults = MutableStateFlow(emptyList<GameModel>())
    private val topRatedGames = MutableStateFlow(emptyList<GameModel>())
    private val stores = MutableStateFlow(emptyList<StoreModelX>())
    private val developers = MutableStateFlow(emptyList<DeveloperModel>())
    private val publishers = MutableStateFlow(emptyList<PublisherModel>())
    private val tags = MutableStateFlow(emptyList<TagModel>())
    private val gameDetailMap = MutableStateFlow<Map<Int , GameModel>>(emptyMap())
    private val gameNewsDetailMap = MutableStateFlow<Map<String , ArticleModel>>(emptyMap())
    private val screenshotMap =  mutableMapOf<Int, MutableStateFlow<List<ScreenshotModel>>>()
    private val youtubeTrailerMap = mutableMapOf<Int, MutableStateFlow<List<ItemModel>>>()
    private val youtubeStreamMap = mutableMapOf<Int , MutableStateFlow<List<StreamItemModel>>>()
    private val gameNewsSearchResults = MutableStateFlow(emptyList<ArticleModel>())


    override suspend fun saveGenres(newObjects: List<GenreModel>) {
        Genres.value = newObjects
    }

    override suspend fun savePlatform(newObjects: List<PlatformModel>) {
        Platform.value = newObjects
    }

    override suspend fun saveUpcoming(newObjects: List<GameModel>) {
        upcomingGames.value = newObjects
    }

    override suspend fun savePlatformWise(newObjects: List<GameModel>) {
        PlatformWiseGames.value = newObjects
    }

    override suspend fun saveGenreWise(newObjects: List<GameModel>) {
        GenresWiseGames.value = newObjects
    }

    override suspend fun saveAll(newObjects: List<GameModel>) {
        AllGames.value = newObjects
    }

    override suspend fun saveSearchResults(newObjects: List<GameModel>) {
        searchResults.value = newObjects
    }

    override suspend fun saveTopRated(newObjects: List<GameModel>) {
        topRatedGames.value = newObjects
    }

    override suspend fun saveStores(newObjects: List<StoreModelX>) {
        stores.value = newObjects
    }

    override suspend fun saveDevelopers(newObjects: List<DeveloperModel>) {
        developers.value = newObjects
    }

    override suspend fun savePublishers(newObjects: List<PublisherModel>) {
       publishers.value = newObjects
    }

    override suspend fun saveTags(newObjects: List<TagModel>) {
        tags.value = newObjects
    }

    override suspend fun saveGameDetail(game: GameModel) {
        gameDetailMap.value = gameDetailMap.value.toMutableMap().apply {
            game.id?.let { put(it,game) }
        }
    }

    override suspend fun saveScreenshots(gameId: Int , newObjects: List<ScreenshotModel>) {
        val flow = screenshotMap.getOrPut(gameId) { MutableStateFlow(emptyList()) }
        flow.value = newObjects
    }

    override suspend fun saveYoutubeTrailers(gameId: Int , trailers: List<ItemModel>) {
        val flow = youtubeTrailerMap.getOrPut(gameId) { MutableStateFlow(emptyList()) }
        flow.value = trailers
    }

    override suspend fun saveYoutubeStreams(
        gameId: Int,
        streams: List<StreamItemModel>
    ) {
        val flow = youtubeStreamMap.getOrPut(gameId) { MutableStateFlow(emptyList()) }
        flow.value = streams
    }

    override suspend fun saveGameNewsSearchResults(newObjects: List<ArticleModel>) {
        gameNewsSearchResults.value = newObjects
    }

    override fun getUpcomingWiseGameById(GameId: Int): Flow<GameModel?> {
        return upcomingGames.map { game ->
            game.find { it.id.toString() == GameId.toString() }
        }
    }

    override fun getPlatformWiseGameById(GameId: Int): Flow<GameModel?> {
        return PlatformWiseGames.map { game ->
            game.find { it.id.toString() == GameId.toString() }
        }
    }

    override fun getGenresWiseGameById(GameId: Int): Flow<GameModel?> {
        return GenresWiseGames.map { game ->
            game.find { it.id.toString() == GameId.toString() }
        }
    }


    override fun getGameById(GameId: Int): Flow<GameModel?> {
        return AllGames.map { game ->
            game.find { it.id.toString() == GameId.toString() }
        }
    }

    override fun getSearchResultById(GameId: Int): Flow<GameModel?>  =
        searchResults.map { game ->
            game.find { it.id.toString() == GameId.toString() }
        }

    override fun getTopRatedGameByTd(GameId: Int): Flow<GameModel?> {
        return topRatedGames.map { game ->
            game.find { it.id.toString() == GameId.toString() }
        }
    }

    override fun getStoreGameById(GameId: Int): Flow<StoreModelX?> {
        return stores.map { game ->
            game.find { it.id.toString() == GameId.toString() }
        }
    }

    override fun getDeveloperGameById(GameId: Int): Flow<DeveloperModel> {
        return developers.map { game ->
            game.find { it.id.toString() == GameId.toString() }!!
        }
    }

    override fun getPublisherGameById(GameId: Int): Flow<PublisherModel> {
        return publishers.map { game ->
            game.find { it.id.toString() == GameId.toString() }!!
        }
    }

    override fun getTagGameById(GameId: Int): Flow<TagModel> {
        return tags.map { game ->
            game.find { it.id.toString() == GameId.toString() }!!
        }
    }

    override fun getGameDetailById(gameId: Int): Flow<GameModel?> {
        return gameDetailMap.map { it[gameId] }
    }

    override fun getGameNewsDetailById(articleUrl: String): Flow<ArticleModel?> {
        return gameNewsDetailMap.map { it[articleUrl] }
    }


    override fun getUpcomingGame(): Flow<List<GameModel>> = upcomingGames
    override fun getSearchResults(): Flow<List<GameModel>>  = searchResults
    override fun getGenres(): Flow<List<GenreModel>> = Genres
    override fun getPlatform(): Flow<List<PlatformModel>> = Platform
    override fun getPlatformWise(): Flow<List<GameModel>> = PlatformWiseGames
    override fun getGenresWise(): Flow<List<GameModel>> = GenresWiseGames
    override fun getAllGame(): Flow<List<GameModel>> = AllGames
    override fun getTopRatedGame(): Flow<List<GameModel>> = topRatedGames
    override fun getStores(): Flow<List<StoreModelX>> = stores
    override fun getDevelopers(): Flow<List<DeveloperModel>> = developers
    override fun getPublishers(): Flow<List<PublisherModel>> = publishers
    override fun getTags(): Flow<List<TagModel>> = tags
    override fun getScreenshots(gameId: Int): Flow<List<ScreenshotModel>> {
        return screenshotMap.getOrPut(gameId) { MutableStateFlow(emptyList()) }
    }

    override fun getYoutubeTrailers(gameId: Int): Flow<List<ItemModel>> {
        return youtubeTrailerMap.getOrPut(gameId) {MutableStateFlow(emptyList())}
    }

    override fun getYoutubeStreams(gameId: Int): Flow<List<StreamItemModel>> {
        return youtubeStreamMap.getOrPut(gameId) { MutableStateFlow(emptyList()) }
    }

    override fun getGameNewsSearchResults(): Flow<List<ArticleModel>> = gameNewsSearchResults

}
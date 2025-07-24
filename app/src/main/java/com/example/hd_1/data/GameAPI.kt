package com.example.hd_1.data

import android.util.Log
import com.example.hd_1.data.gameNewsModel.ArticleModel
import com.example.hd_1.data.gameNewsModel.GameNewsModel
import com.example.hd_1.data.rawgModels.DeveloperModel
import com.example.hd_1.data.rawgModels.GenreModel
import com.example.hd_1.data.rawgModels.PlatformModel
import com.example.hd_1.data.rawgModels.PublisherModel
import com.example.hd_1.data.rawgModels.ScreenshotModel
import com.example.hd_1.data.rawgModels.StoreModelX
import com.example.hd_1.data.rawgModels.TagModel
import com.example.hd_1.data.streamModel.StreamItemModel
import com.example.hd_1.data.streamModel.YoutubeStreamModel
import com.example.hd_1.data.youtubeTrailerModel.ItemModel
import com.example.hd_1.data.youtubeTrailerModel.YoutubeTrailerModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import java.util.concurrent.CancellationException

interface GameAPI {
    suspend fun getTopRatedGames(): List<GameModel>
    suspend fun getGamesGenres(): List<GenreModel>
    suspend fun getGamesPlatforms(): List<PlatformModel>
    suspend fun getAllGames(): List<GameModel>
    suspend fun getSearchGames(query: String): List<GameModel>
    suspend fun getGenreWiseGames(query: String): List<GameModel>
    suspend fun getPlatformWiseGames(query: String): List<GameModel>
    suspend fun getUpcomingGames(): List<GameModel>
    suspend fun getGamesStores(): List<StoreModelX>
    suspend fun getGamesDevelopers(): List<DeveloperModel>
    suspend fun getGamesPublisher(): List<PublisherModel>
    suspend fun getGamesTags(): List<TagModel>
    suspend fun getGameDetailById(gameId: Int): GameModel?
    suspend fun getGameScreenshotById(gameId: Int): List<ScreenshotModel>
    suspend fun searchGameTrailers(query: String): List<ItemModel>
    suspend fun searchGameStreams(query: String): List<StreamItemModel>
    suspend fun searchGamesNews(query: String): List<ArticleModel>
}

val BASE_URL = "https://api.rawg.io/api"
val API_GAME_NEWS = "https://newsapi.org/v2/everything"
val NEWS_API_KEY = "4ea7860f0e894b58a289abe944f20459"
val API_KEY = "61474c28b07c427fa14b99d8f7ce5826"
val TRAILER_API_KEY = "AIzaSyCzZT9UPxud--yPanODYtuSEurazQF3Ei4"
val API_TOPRATED = "$BASE_URL/stores?key=$API_KEY"
val API_GENRES = "$BASE_URL/genres?key=$API_KEY"
val API_PLATFORMWISE = "$BASE_URL/platforms?key=$API_KEY"
val API_GAMES = "$BASE_URL/games?key=$API_KEY&page=1&page_size=40"
val API_GENREWISE = "$BASE_URL/games?genres=action&key=$API_KEY"
val API_PLATFORM = "$BASE_URL/platforms?key=$API_KEY"
val API_UPCOMING = "$BASE_URL/games?dates=2025-06-08,2026-12-31&key=$API_KEY"
val API_STORE = "$BASE_URL/stores?key=$API_KEY&page_size=40"
val API_DEVELOPER = "$BASE_URL/developers?key=$API_KEY"
val API_PUBLISHER = "$BASE_URL/publishers?key=$API_KEY"
val API_TAG = "$BASE_URL/tags?key=$API_KEY"



class KtorGameApi(private val client: HttpClient) : GameAPI {
    override suspend fun getTopRatedGames(): List<GameModel> {
        return try {
            val response: GameResponseModel = client.get(API_TOPRATED).body()
            response.games ?: emptyList()
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            e.printStackTrace()
            emptyList()
        }
    }

    override suspend fun getGamesGenres(): List<GenreModel> {
        return try {
            val response: GenreResponseModel = client.get(API_GENRES).body()
            response.genres ?: emptyList()
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            e.printStackTrace()
            emptyList()
        }
    }

    override suspend fun getGamesPlatforms(): List<PlatformModel> {
        return try {
            val response: PlatformResponseModel = client.get(API_PLATFORM).body()
            response.platforms ?: emptyList()
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            e.printStackTrace()
            emptyList()
        }
    }

    override suspend fun getAllGames(): List<GameModel> {
        val allGames = mutableListOf<GameModel>()
        var nextUrl: String? = "$BASE_URL/games?key=$API_KEY&page=1&page_size=40"

        return try {
            while (nextUrl != null) {
                val response: GameResponseModel = client.get(nextUrl).body()
                allGames.addAll(response.games.orEmpty())
                nextUrl = response.next
            }
            allGames
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            e.printStackTrace()
            emptyList()
        }
    }

    override suspend fun getSearchGames(query: String): List<GameModel> {
        return try {
            val response: GameResponseModel = client.get("$BASE_URL/games") {
                parameter("search", query)
                parameter("key", API_KEY)
            }.body()
            response.games ?: emptyList()
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            e.printStackTrace()
            emptyList()
        }
    }

    override suspend fun getGenreWiseGames(query: String): List<GameModel> {
        return try {
            val response: GameResponseModel = client.get("$BASE_URL/games") {
                parameter("genres", query)
                parameter("page_size", 50) // Best for lazy loading
                parameter("key", API_KEY)
            }.body()
            response.games ?: emptyList()
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            e.printStackTrace()
            emptyList()
        }
    }

    override suspend fun getPlatformWiseGames(query: String): List<GameModel> {
        return try {
            val response: GameResponseModel = client.get("$BASE_URL/games") {
                parameter("platforms", query)
                parameter("page_size", 50) // Best for lazy loading
                parameter("key", API_KEY)
            }.body()
            response.games ?: emptyList()
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            e.printStackTrace()
            emptyList()
        }
    }


    override suspend fun getUpcomingGames(): List<GameModel> {
        return try {
            val response: GameResponseModel = client.get(API_UPCOMING).body()
            response.games ?: emptyList()
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            e.printStackTrace()
            emptyList()
        }
    }

    override suspend fun getGamesStores(): List<StoreModelX> {
        return try {
            val response: StoreResponseModel = client.get(API_STORE).body()
            response.stores ?: emptyList()
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            e.printStackTrace()
            emptyList()
        }
    }

    override suspend fun getGamesDevelopers(): List<DeveloperModel> {
        return try {
            val response: DeveloperResponseModel = client.get(API_DEVELOPER).body()
            Log.d("API_RAW", response.toString())
            response.developers ?: emptyList()

        } catch (e: Exception) {
            if (e is CancellationException) throw e
            e.printStackTrace()
            emptyList()
        }
    }

    override suspend fun getGamesPublisher(): List<PublisherModel> {
        return try {
            val response: PublisherResponseModel = client.get(API_PUBLISHER).body()
            response.publishers ?: emptyList()
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            e.printStackTrace()
            emptyList()
        }
    }

    override suspend fun getGamesTags(): List<TagModel> {
        return try {
            val response: TagResponseModel = client.get(API_TAG).body()
            response.tags ?: emptyList()
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            e.printStackTrace()
            emptyList()
        }
    }

    override suspend fun getGameDetailById(gameId: Int): GameModel? {
        return try {
            client.get("$BASE_URL/games/$gameId") {
                parameter("key", API_KEY)
            }.body()
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            e.printStackTrace()
            null
        }
    }

    override suspend fun getGameScreenshotById(gameId: Int): List<ScreenshotModel> {
        return try {
            val url = "$BASE_URL/games/$gameId/screenshots?key=$API_KEY"
            val response: ScreenshotResponseModel = client.get(url).body()
            response.results ?: emptyList()
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            e.printStackTrace()
            emptyList()
        }
    }

    //youtube-trailer
    override suspend fun searchGameTrailers(query: String): List<ItemModel> {
        return try {
            val response: YoutubeTrailerModel = client.get("https://www.googleapis.com/youtube/v3/search") {
                parameter("part", "snippet")
                parameter("type", "video")
                parameter("maxResults", 3)
                parameter("q", "$query game trailer")
                parameter("key", TRAILER_API_KEY)
            }.body()
            response.items ?: emptyList()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    override suspend fun searchGameStreams(query: String): List<StreamItemModel> {
        return try {
            val response: YoutubeStreamModel = client.get("https://www.googleapis.com/youtube/v3/search") {
                parameter("part", "snippet")
                parameter("type", "video")
                parameter("eventType", "live")
                parameter("videoCategoryId", 20)
                parameter("maxResults", 5)
                parameter("q", "$query live gameplay")
                parameter("key", TRAILER_API_KEY)
            }.body()
            response.items ?: emptyList()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    override suspend fun searchGamesNews(query: String): List<ArticleModel> {
        return try {
            val response: GameNewsModel = client.get(API_GAME_NEWS) {
                parameter("q", "$query game")
                parameter("sortBy", "relevancy")
                parameter("language", "en")
                parameter("apiKey", NEWS_API_KEY)
            }.body()
            response.articles ?: emptyList()
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            e.printStackTrace()
            emptyList()
        }
    }
}




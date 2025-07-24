package com.example.hd_1.data


import com.example.hd_1.data.rawgModels.AddedByStatusModel
import com.example.hd_1.data.rawgModels.DeveloperModel
import com.example.hd_1.data.rawgModels.EsrbRatingModel
import com.example.hd_1.data.rawgModels.GenreModel
import com.example.hd_1.data.rawgModels.MetacriticPlatformModel
import com.example.hd_1.data.rawgModels.ParentPlatformModel
import com.example.hd_1.data.rawgModels.PlatformModelXX
import com.example.hd_1.data.rawgModels.PublisherModel
import com.example.hd_1.data.rawgModels.RatingModel
import com.example.hd_1.data.rawgModels.ReactionsModel
import com.example.hd_1.data.rawgModels.ScreenshotModel
import com.example.hd_1.data.rawgModels.StoreModel
import com.example.hd_1.data.rawgModels.TagModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameModel(
    @SerialName("achievements_count")
    val achievementsCount: Int? = 0,
    @SerialName("added")
    val added: Int? = 0,
    @SerialName("added_by_status")
    val addedByStatus: AddedByStatusModel? = AddedByStatusModel(),
    @SerialName("additions_count")
    val additionsCount: Int? = 0,
    @SerialName("alternative_names")
    val alternativeNames: List<String>? = listOf(),
    @SerialName("background_image")
    val backgroundImage: String? = "",
    @SerialName("background_image_additional")
    val backgroundImageAdditional: String? = "",
    @SerialName("creators_count")
    val creatorsCount: Int? = 0,
    @SerialName("description")
    val description: String? = "",
    @SerialName("description_raw")
    val descriptionRaw: String? = "",
    @SerialName("developers")
    val developers: List<DeveloperModel>? = listOf(),
    @SerialName("dominant_color")
    val dominantColor: String? = "",
    @SerialName("esrb_rating")
    val esrbRating: EsrbRatingModel? = EsrbRatingModel(),
    @SerialName("game_series_count")
    val gameSeriesCount: Int? = 0,
    @SerialName("genres")
    val genres: List<GenreModel>? = listOf(),
    @SerialName("id")
    val id: Int? = 0,
    @SerialName("metacritic")
    val metacritic: Int? = 0,
    @SerialName("metacritic_platforms")
    val metacriticPlatforms: List<MetacriticPlatformModel>? = listOf(),
    @SerialName("metacritic_url")
    val metacriticUrl: String? = "",
    @SerialName("movies_count")
    val moviesCount: Int? = 0,
    @SerialName("name")
    val name: String? = "",
    @SerialName("name_original")
    val nameOriginal: String? = "",
    @SerialName("parent_achievements_count")
    val parentAchievementsCount: Int? = 0,
    @SerialName("parent_platforms")
    val parentPlatforms: List<ParentPlatformModel>? = listOf(),
    @SerialName("parents_count")
    val parentsCount: Int? = 0,
    @SerialName("platforms")
    val platforms: List<PlatformModelXX>? = listOf(),
    @SerialName("screenshots")
    val screenshots: List<ScreenshotModel>? = listOf(),
    @SerialName("playtime")
    val playtime: Int? = 0,
    @SerialName("publishers")
    val publishers: List<PublisherModel>? = listOf(),
    @SerialName("rating")
    val rating: Double? = 0.0,
    @SerialName("rating_top")
    val ratingTop: Int? = 0,
    @SerialName("ratings")
    val ratings: List<RatingModel>? = listOf(),
    @SerialName("ratings_count")
    val ratingsCount: Int? = 0,
    @SerialName("reactions")
    val reactions: ReactionsModel? = ReactionsModel(),
    @SerialName("reddit_count")
    val redditCount: Int? = 0,
    @SerialName("reddit_description")
    val redditDescription: String? = "",
    @SerialName("reddit_logo")
    val redditLogo: String? = "",
    @SerialName("reddit_name")
    val redditName: String? = "",
    @SerialName("reddit_url")
    val redditUrl: String? = "",
    @SerialName("released")
    val released: String? = "",
    @SerialName("reviews_count")
    val reviewsCount: Int? = 0,
    @SerialName("reviews_text_count")
    val reviewsTextCount: Int? = 0,
    @SerialName("saturated_color")
    val saturatedColor: String? = "",
    @SerialName("screenshots_count")
    val screenshotsCount: Int? = 0,
    @SerialName("slug")
    val slug: String? = "",
    @SerialName("stores")
    val stores: List<StoreModel>? = listOf(),
    @SerialName("suggestions_count")
    val suggestionsCount: Int? = 0,
    @SerialName("tags")
    val tags: List<TagModel>? = listOf(),
    @SerialName("tba")
    val tba: Boolean? = false,
    @SerialName("twitch_count")
    val twitchCount: Int? = 0,
    @SerialName("updated")
    val updated: String? = "",
    @SerialName("website")
    val website: String? = "",
    @SerialName("youtube_count")
    val youtubeCount: Int? = 0
)
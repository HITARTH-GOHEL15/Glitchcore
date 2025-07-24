package com.example.hd_1.user_interface.game_detail

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.hd_1.data.GameModel
import com.example.hd_1.data.rawgModels.ScreenshotModel
import com.example.hd_1.data.streamModel.StreamItemModel
import com.example.hd_1.data.youtubeTrailerModel.ItemModel
import com.example.hd_1.data.youtubeTrailerModel.YoutubeTrailerModel
import com.example.hd_1.ui.theme.sora_bold
import com.example.hd_1.ui.theme.sora_extraBold
import com.example.hd_1.user_interface.game_detail.components.ImageChipSection
import com.example.hd_1.user_interface.game_detail.components.PlatformReqSection
import com.example.hd_1.user_interface.game_detail.components.PlatformsSection
import com.example.hd_1.user_interface.game_detail.components.ScreenshotList
import com.example.hd_1.user_interface.game_detail.components.StreamList
import com.example.hd_1.user_interface.game_detail.components.TrailerList

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GameDetails(
    game: GameModel,
    screenshots: List<ScreenshotModel>,
    trailers: List<ItemModel>,
    streams: List<StreamItemModel>
) {
    val uriHandler = LocalUriHandler.current
    LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(top = 120.dp , bottom = 30.dp , start = 12.dp , end = 12.dp)
        ) {
           item {
               //Game_title
               game.name?.let {
                   Text(
                       text = it,
                       style = MaterialTheme.typography.titleLarge,
                       color = MaterialTheme.colorScheme.secondary,
                       fontFamily = sora_extraBold,
                       fontWeight = FontWeight.ExtraBold
                   )
               }
               Spacer(modifier = Modifier.padding(2.dp))
               Row {
                   //released
                   game.released?.let {
                       Text(
                           text = it,
                           style = MaterialTheme.typography.bodyMedium,
                           overflow = TextOverflow.Ellipsis,
                           fontFamily = sora_bold,
                           color = MaterialTheme.colorScheme.tertiary,
                           lineHeight = 24.sp
                       )
                   }
                   Spacer(modifier = Modifier.padding(8.dp))
                       Text(
                           text = "${game.playtime.toString()} hrs",
                           style = MaterialTheme.typography.bodyMedium,
                           overflow = TextOverflow.Ellipsis,
                           fontFamily = sora_bold,
                           color = MaterialTheme.colorScheme.tertiary,
                           lineHeight = 24.sp
                       )
               }
               Spacer(modifier = Modifier.padding(4.dp))
               //game-poster
               Box(
                   modifier = Modifier
                       .fillMaxWidth()
                       .background(
                           color = MaterialTheme.colorScheme.secondary.copy(0.1f),
                           shape = MaterialTheme.shapes.medium
                       ),
                   contentAlignment = Alignment.Center
               ) {
                   AsyncImage(
                       model = game.backgroundImage,
                       contentDescription = "thumbnail",
                       contentScale = ContentScale.Crop,
                       modifier = Modifier
                           .clip(MaterialTheme.shapes.medium)
                           .height(200.dp)
                   )
               }
               Spacer(modifier = Modifier.padding(2.dp))
               //game-rating , metaCredit
               GameRatingRow(
                   gameRating = game.rating.toString(),
                   twitchCount = game.twitchCount.toString(),
                   metaCredit = game.metacritic.toString()
               )
               //game-Raw-description
               game.descriptionRaw?.let {
                   Text(
                       text = it,
                       style = MaterialTheme.typography.bodyMedium,
                       overflow = TextOverflow.Ellipsis,
                       fontFamily = sora_bold,
                       color = MaterialTheme.colorScheme.onPrimary.copy(0.9f),
                       lineHeight = 24.sp,
                       modifier = Modifier
                           .padding(vertical = 10.dp)
                   )
               }
           }
        item {
            Row(
                modifier = Modifier
                    .padding(bottom = 8.dp)
            ) {
                game.website?.let {
                Text(
                    text = "Website :",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontFamily = sora_extraBold,
                    fontWeight = FontWeight.ExtraBold
                )
                Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = it,
                        style = MaterialTheme.typography.titleSmall.copy(
                            textDecoration = TextDecoration.Underline
                        ),
                        color = MaterialTheme.colorScheme.tertiary,
                        fontFamily = sora_extraBold,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier
                            .clickable {
                                uriHandler.openUri(it)
                            }
                    )
                }
            }
        }
            item {
                screenshots.let {
                    Text(
                        text = "Screenshots",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSecondary,
                        fontFamily = sora_extraBold,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    ScreenshotList(it)
                }
            }
            item {
                if (trailers.isNotEmpty()) {
                    Text(
                        text = "Trailers",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSecondary,
                        fontFamily = sora_extraBold,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TrailerList(trailers)
                } else {
                }
            }
        item {
            if (streams.isNotEmpty()) {
                Text(
                    text = "ongoing streams",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontFamily = sora_extraBold,
                    fontWeight = FontWeight.ExtraBold
                )
                Spacer(modifier = Modifier.height(8.dp))
                StreamList(streams)
            } else {
            }
        }

        item {
                game.parentPlatforms?.let {
                    PlatformsSection(it)
                }
            }
            item {
                game.platforms?.let {
                    val reqItems = it.map { req ->
                        Triple(req.platform?.name, req.requirements?.recommended, req.requirements?.minimum)
                    }
                    PlatformReqSection(
                        title = "Platform Requirements",
                        reqItems
                    )
                }
            }
            item {

                // Developers
                game.developers?.let {
                    val devItems = it.map { dev -> dev.name to dev.imageBackground }
                    ImageChipSection("Developers", devItems)
                }

                // Publishers
                game.publishers?.let {
                    val pubItems = it.map { pub -> pub.name to pub.imageBackground }
                    ImageChipSection("Publishers", pubItems)
                }

                // Stores
                game.stores?.let {
                    val storeItems = it.map { storeModel ->
                        storeModel.store?.name to storeModel.store?.imageBackground
                    }
                    ImageChipSection("Available on", storeItems)
                }

                // Tags
                game.tags?.let {
                    val tagItems = it.map { tag -> tag.name to tag.imageBackground }
                    ImageChipSection("Tags", tagItems)
                }
            }
        }
    }

@Composable
fun GameRatingRow(
    gameRating: String,
    twitchCount: String,
    metaCredit: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        //rating
        Column(
            modifier = Modifier
                .align(Alignment.CenterStart)
        ) {
            //game-ratingIcon
            Icon(
                imageVector = Icons.Outlined.Star,
                contentDescription = "rating",
                tint = MaterialTheme.colorScheme.secondary
            )
            //game-rating
            Text(
                text = "$gameRating/5",
                style = MaterialTheme.typography.bodyMedium,
                overflow = TextOverflow.Ellipsis,
                fontFamily = sora_bold,
                color = MaterialTheme.colorScheme.tertiary,
                lineHeight = 24.sp,
                modifier = Modifier
            )
        }
        //twitchCount
        Column(
            modifier = Modifier
                .align(Alignment.Center)
        ) {
            Text(
                text = "TwitchStreams",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.secondary,
                fontFamily = sora_extraBold,
                fontWeight = FontWeight.ExtraBold
            )
            //game-rating
            Text(
                text = twitchCount,
                style = MaterialTheme.typography.bodyMedium,
                overflow = TextOverflow.Ellipsis,
                fontFamily = sora_bold,
                color = MaterialTheme.colorScheme.tertiary,
                lineHeight = 24.sp,
                modifier = Modifier
            )
        }
        //meta-credit
        Column(
            modifier = Modifier
                .align(Alignment.CenterEnd)
        ) {
            Text(
                text = "MetaCredit",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.secondary,
                fontFamily = sora_extraBold,
                fontWeight = FontWeight.ExtraBold
            )
            //game-rating
            Text(
                text = metaCredit,
                style = MaterialTheme.typography.bodyMedium,
                overflow = TextOverflow.Ellipsis,
                fontFamily = sora_bold,
                color = MaterialTheme.colorScheme.tertiary,
                lineHeight = 24.sp,
                modifier = Modifier
            )
        }
    }
}

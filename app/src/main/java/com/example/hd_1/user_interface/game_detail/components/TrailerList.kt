package com.example.hd_1.user_interface.game_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.hd_1.data.youtubeTrailerModel.ItemModel
import com.example.hd_1.data.youtubeTrailerModel.YoutubeTrailerModel
import com.example.hd_1.ui.theme.sora_bold

@Composable
fun TrailerList(trailers: List<ItemModel>) {
    val uriHandler = LocalUriHandler.current

    LazyRow(contentPadding = PaddingValues(horizontal = 8.dp)) {
        items(trailers) { trailer ->
            val videoId = trailer.id?.videoId
            val thumbnail = trailer.snippet?.thumbnails?.high?.url ?: ""
            val title = trailer.snippet?.title ?: ""

            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .width(340.dp)
                    .clickable {
                        uriHandler.openUri("https://www.youtube.com/watch?v=$videoId")
                    }
            ) {
                AsyncImage(
                    model = thumbnail,
                    contentDescription = title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .background(color = MaterialTheme.colorScheme.secondary.copy(0.1f) , shape = RoundedCornerShape(20.dp))
                        .clip(RoundedCornerShape(20.dp)),
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = title,
                    fontFamily = sora_bold,
                    color = MaterialTheme.colorScheme.onPrimary.copy(0.8f),
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2
                )
            }
        }
    }
}

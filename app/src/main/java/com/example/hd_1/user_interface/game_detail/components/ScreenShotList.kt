package com.example.hd_1.user_interface.game_detail.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.hd_1.data.rawgModels.ScreenshotModel

@Composable
fun ScreenshotList(screenshots: List<ScreenshotModel>) {
    LazyRow(contentPadding = PaddingValues(horizontal = 8.dp)) {
        items(screenshots) { screenshot ->
            AsyncImage(
                model = screenshot.image,
                contentDescription = "Screenshot",
                modifier = Modifier
                    .padding(8.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .height(300.dp)
                    .width(400.dp),
                contentScale = ContentScale.Crop
            )
        }
    }
}

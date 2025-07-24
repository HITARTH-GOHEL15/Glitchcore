package com.example.hd_1.user_interface.main.platforms.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.hd_1.data.rawgModels.PlatformModel
import com.example.hd_1.ui.theme.sora_extraBold

@Composable
fun PlatformRowCard(
    platforms: PlatformModel,
    onPlatformClick: () -> Unit
) {
    Column {
        Box(
            modifier = Modifier
                .width(250.dp)
                .height(250.dp)
                .padding(start = 10.dp , end = 10.dp)
                .clickable {
                   onPlatformClick()
                }
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = MaterialTheme.shapes.medium
                ),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = platforms.imageBackground,
                contentDescription = "thumbnail",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .height(450.dp)
            )
        }
        platforms.name?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimary,
                fontFamily = sora_extraBold,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier
                    .padding(10.dp)
            )
        }
    }
}
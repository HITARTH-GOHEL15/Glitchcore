package com.example.hd_1.user_interface.game_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.hd_1.ui.theme.sora_bold
import com.example.hd_1.ui.theme.sora_extraBold

@Composable
fun ImageChipItem(name: String? , imageUrl: String?) {
    Row(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f), RoundedCornerShape(20.dp))
            .padding(horizontal = 12.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        imageUrl?.let {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(it)
                    .crossfade(true)
                    .build(),
                contentDescription = name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(35.dp)
                    .background(color = MaterialTheme.colorScheme.secondary.copy(0.1f) , shape = MaterialTheme.shapes.large)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.padding(3.dp))
        }
        Text(
            text = name ?: "",
            fontFamily = sora_bold,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding(horizontal = 12.dp , vertical = 6.dp),
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
fun ImageChipSection(title: String , items: List<Pair<String? , String?>>) {
    if (items.isNotEmpty()) {
        Column(
            modifier = Modifier.padding(end = 8.dp , bottom = 8.dp , top = 8.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSecondary,
                fontFamily = sora_extraBold,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(Modifier.height(8.dp))
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items.forEach { (name, image) ->
                    ImageChipItem(name, image)
                }
            }
        }
    }
}
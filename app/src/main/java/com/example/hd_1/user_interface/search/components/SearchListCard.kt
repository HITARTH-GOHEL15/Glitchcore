package com.example.hd_1.user_interface.search.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.hd_1.data.GameModel
import com.example.hd_1.ui.theme.HD_1Theme
import com.example.hd_1.ui.theme.sora_extraBold

@Composable
fun SearchListCard(
    game: GameModel,
    onGameClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable {
                onGameClick()
            }
            .border(width = 0.5.dp , color = MaterialTheme.colorScheme.secondary.copy(0.1f) , shape = MaterialTheme.shapes.medium)
            .background(color = MaterialTheme.colorScheme.background, shape = MaterialTheme.shapes.medium),
        contentAlignment = Alignment.Center
    ) {
        Row {
            AsyncImage(
                model = game.backgroundImage,
                contentDescription = "thumbnail",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .weight(0.3f)
                    .height(135.dp)
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Column(
                modifier = Modifier
                    .weight(0.7f)
                    .padding(vertical = 10.dp)
            ) {
                game.name?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.secondary,
                        fontFamily = sora_extraBold,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
                game.released?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyMedium,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.tertiary,
                        lineHeight = 24.sp
                    )
                }
                Text(
                    text = game.rating.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.tertiary,
                    lineHeight = 24.sp
                )
                game.metacritic.toString()?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyMedium,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.tertiary,
                        lineHeight = 24.sp
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun SearchListCardPreview() {
    HD_1Theme {

    }
}
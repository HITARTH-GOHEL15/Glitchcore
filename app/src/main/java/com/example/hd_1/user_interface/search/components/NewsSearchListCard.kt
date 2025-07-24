package com.example.hd_1.user_interface.search.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.hd_1.data.gameNewsModel.ArticleModel
import com.example.hd_1.ui.theme.sora_bold
import com.example.hd_1.ui.theme.sora_extraBold

@Composable
fun NewsSearchListCard(
    article: ArticleModel,
    onArticleClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable {
              onArticleClick()
            }
            .border(width = 0.5.dp , color = MaterialTheme.colorScheme.secondary.copy(0.1f) , shape = MaterialTheme.shapes.medium)
            .background(color = MaterialTheme.colorScheme.background, shape = MaterialTheme.shapes.medium),
        contentAlignment = Alignment.Center
    ) {
            Column(
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .height(500.dp)
            ) {
                AsyncImage(
                    model = article.urlToImage,
                    contentDescription = "thumbnail",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(MaterialTheme.shapes.medium)
                        .weight(0.4f)
                )
                Spacer(modifier = Modifier.padding(4.dp))
                article.title?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.secondary,
                        fontFamily = sora_extraBold,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
                article.publishedAt?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyMedium,
                        fontFamily = sora_bold,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.tertiary,
                        lineHeight = 24.sp
                    )
                }
                Text(
                    text = article.description.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    fontFamily = sora_bold,
                    color = MaterialTheme.colorScheme.onPrimary,
                    lineHeight = 24.sp
                )
            }
        }
    }

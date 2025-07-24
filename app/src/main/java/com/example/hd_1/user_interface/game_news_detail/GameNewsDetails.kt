package com.example.hd_1.user_interface.game_news_detail

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
import com.example.hd_1.data.gameNewsModel.ArticleModel
import com.example.hd_1.ui.theme.sora_bold
import com.example.hd_1.ui.theme.sora_extraBold

@Composable
fun GameNewsDetails(
    article: ArticleModel
) {
    val uriHandler = LocalUriHandler.current
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(top = 120.dp , bottom = 30.dp , start = 12.dp , end = 12.dp)
    ) {
        item {
            //article_title
            article.title?.let {
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
                //author
                article.author?.let {
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
                article.source?.name?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.tertiary,
                        fontFamily = sora_extraBold,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
                Spacer(modifier = Modifier.padding(8.dp))
                //publish-date
                Text(
                    text = article.publishedAt.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = sora_bold,
                    color = MaterialTheme.colorScheme.tertiary,
                    lineHeight = 24.sp
                )

            }
            Spacer(modifier = Modifier.padding(4.dp))
            //article-poster
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
                    model = article.urlToImage,
                    contentDescription = "thumbnail",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(MaterialTheme.shapes.medium)
                        .height(400.dp)
                )
            }

            //description
            article.description?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = sora_bold,
                    color = MaterialTheme.colorScheme.onPrimary.copy(0.9f),
                    lineHeight = 26.sp,
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                )
            }
        }
        item {
            //content
            article.content?.let {
                    Text(
                        text = "Article Preview : ",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.secondary,
                        fontFamily = sora_extraBold,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodyMedium,
                            overflow = TextOverflow.Ellipsis,
                            fontFamily = sora_bold,
                            color = MaterialTheme.colorScheme.onPrimary.copy(0.9f),
                            lineHeight = 24.sp,
                            modifier = Modifier.padding(vertical = 10.dp)
                        )
            }
        }
        item {
            Row(
                modifier = Modifier
                    .padding(bottom = 8.dp)
            ) {
                article.url?.let {
                    Text(
                        text = "Read Full Article : ",
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
    }
}
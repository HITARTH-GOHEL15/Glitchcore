package com.example.hd_1.user_interface.main.platforms

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.hd_1.data.rawgModels.PlatformModel
import com.example.hd_1.user_interface.main.platforms.components.PlatformRowCard
import org.koin.compose.viewmodel.koinViewModel

@SuppressLint("SuspiciousIndentation")
@Composable
fun PlatformsRowScreen(
    modifier: Modifier,
    navigateToDetails: (genreId: Int) -> Unit
) {
    val viewModel = koinViewModel<PlatformRowViewModel>()
    val platfroms by viewModel.PlatformsResults.collectAsStateWithLifecycle()

        AnimatedContent(
            platfroms.isNotEmpty()
        ) { platformAvailable ->
            if (
                platformAvailable
            ) {
                PlatformRow(
                    platfroms = platfroms,
                    onPlatformsClick = navigateToDetails
                )
            }
        }
    }



@Composable
fun PlatformRow(
    platfroms: List<PlatformModel>,
    onPlatformsClick: (Int) -> Unit,
    modifier: Modifier = Modifier
)  {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = androidx.compose.foundation.layout.WindowInsets.safeDrawing.asPaddingValues()
    ) {
        items(
            platfroms,
            key = {
                it.id.toString()
            }
        ) { platfroms ->
            PlatformRowCard(
              platforms = platfroms,
                onPlatformClick = {
                    platfroms.id?.let {
                        onPlatformsClick(it)
                    }
                }
            )
        }
    }
}
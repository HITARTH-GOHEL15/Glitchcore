package org.example.hito3.ui.main.genres

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.hd_1.data.rawgModels.GenreModel
import com.example.hd_1.user_interface.main.genres.GenresListViewModel
import org.example.hito3.ui.main.genres.components.GenresRowCard
import org.koin.compose.viewmodel.koinViewModel

@SuppressLint("SuspiciousIndentation")
@Composable
fun GenresRowScreen(
    modifier: Modifier,
    navigateToDetails: (genreId: Int) -> Unit
) {
    val viewModel = koinViewModel<GenresListViewModel>()
    val genres by viewModel.GenresResults.collectAsStateWithLifecycle()


        AnimatedContent(
            genres.isNotEmpty()
        ) { genreAvailable ->
            if (genreAvailable) {
                GenresRow(
                    genres,
                    onGenresClick = navigateToDetails
                )
            }
        }
    }








@Composable
fun GenresRow(
    genres: List<GenreModel>,
    onGenresClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = WindowInsets.safeDrawing.asPaddingValues()
    ) {
        items(
            genres,
            key = {it.id.toString()}
        ) { genres ->
            GenresRowCard(
                genres = genres,
                onGenreClick = {
                    genres.id?.let {
                        onGenresClick(it)
                    }
                }
            )
        }
    }
}

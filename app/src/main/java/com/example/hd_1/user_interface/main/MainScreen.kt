package org.example.hito3.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.isTraceInProgress
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hd_1.ui.theme.sora_extraBold
import com.example.hd_1.user_interface.main.platforms.PlatformsRowScreen
import com.example.hd_1.user_interface.main.upcoming.UpcomingRowScreen
import org.example.hito3.ui.main.genres.GenresRowScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    navigateToGenreDetails: (genreId: Int) -> Unit,
    navigateToUpcomingDetails: (gameId: Int) -> Unit,
    navigateToPlatformGameList: (gameId: Int) -> Unit
) {

    if (!isTraceInProgress()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp, bottom = 100.dp)
        ) {
            item {
                Text(
                    text = "Hi , Glitchers!",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontFamily = sora_extraBold,
                    fontSize = 50.sp,
                    modifier = Modifier
                        .padding(start = 13.dp)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Genres",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.secondary,
                        fontSize = 25.sp,
                        fontFamily = sora_extraBold,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(start = 13.dp)
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                    GenresRowScreen(
                        navigateToDetails = navigateToGenreDetails,
                        modifier = Modifier
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Platforms",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.secondary,
                        fontSize = 25.sp,
                        fontFamily = sora_extraBold,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(start = 13.dp)
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                    PlatformsRowScreen(
                        navigateToDetails = navigateToPlatformGameList,
                        modifier = Modifier
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Upcoming",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.secondary,
                        fontSize = 25.sp,
                        fontFamily = sora_extraBold,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(start = 13.dp)
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                    UpcomingRowScreen(
                        navigateToDetails = navigateToUpcomingDetails,
                        modifier = Modifier
                    )
                }
            }
        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }
}

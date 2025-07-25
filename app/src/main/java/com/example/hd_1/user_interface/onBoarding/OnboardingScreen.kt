package com.example.hd_1.user_interface.onBoarding

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentRecomposeScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.hd_1.MainActivity
import com.example.hd_1.R
import com.example.hd_1.navigation.Screens
import com.example.hd_1.ui.theme.sora_bold
import com.example.hd_1.ui.theme.sora_extraBold
import com.example.hd_1.ui.theme.sora_medium
import com.example.hd_1.ui.theme.sora_regular
import kotlinx.coroutines.launch


@Composable
fun OnboardingScreen(
    navController: NavController,
    context: MainActivity
) {
    val animations = listOf(
        R.raw.animation_1,
        R.raw.animation_2,
        R.raw.animation_3
    )

    val titles = listOf(
        "Explore Thousands of PC Games",
        "Steam Game & Player IDs",
        "Join the Discussion Hub"
    )

    val descriptions = listOf(
        "Follow clear, step-by-step instructions to download and install games quickly and safely—no confusion, no hassle.",
        "Find Steam Game IDs and Player Profiles easily for linking, tracking, or launching games",
        "Connect with other gamers! Share opinions, ask questions, and join conversations about gameplay, mods, and updates."
    )

    val pagerState = rememberPagerState(
        pageCount = { animations.size }
    )

    Column(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.primary)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.wrapContentSize()
        ) { currentPage ->
            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(26.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(animations[currentPage]))
                LottieAnimation(
                    composition = composition,
                    iterations = LottieConstants.IterateForever,
                    modifier = Modifier.size(400.dp)
                )
                Text(
                    text = titles[currentPage],
                    textAlign = TextAlign.Center,
                    fontSize = 25.sp,
                    fontFamily = sora_extraBold,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary
                )
                Spacer(modifier = Modifier.padding(7.dp))
                Text(
                    text = descriptions[currentPage],
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    fontFamily = sora_medium,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
        }
        PageIndicator(pageCount = animations.size,
            currentpage = pagerState.currentPage,
            modifier = Modifier.padding(60.dp)
        )

    }
    ButtonSection(
        pagerState = pagerState,
        onClick = {
            onBoardingIsFinished(context)
            navController.navigate(Screens.LogInScreenRoute)
        }
    )
}
@SuppressLint("UnrememberedMutableState")

@Composable
fun ButtonSection(
    pagerState: PagerState,
    onClick: () -> Unit
){
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier.fillMaxSize().padding(30.dp)
    ){
        if (pagerState.currentPage != 2){
            Text(
                text = "Next",
                fontFamily = sora_regular,
                modifier = Modifier.align(Alignment.BottomEnd)
                    .clickable {
                        scope.launch {
                            val nextPage = pagerState.currentPage + 1
                            pagerState.scrollToPage(nextPage)
                        }
                    },
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.secondary

            )
            Text(
                text = "Back",
                fontFamily = sora_regular,
                modifier = Modifier.align(Alignment.BottomStart).clickable{
                    scope.launch {
                        val prevPage = pagerState.currentPage -1
                        if (prevPage>=0){
                            pagerState.scrollToPage(prevPage)
                        }
                    }
                },
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.tertiary
            )
        }else
        {
            OutlinedButton(
                onClick = onClick,
                modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                Text(
                    text = "Get Started",
                    fontSize = 22.sp,
                    fontFamily = sora_bold,
                    fontWeight = FontWeight.Light,
                    color = MaterialTheme.colorScheme.onPrimary

                )
            }
        }
    }
}



@Composable
fun PageIndicator(
    pageCount: Int,
    currentpage: Int,
    modifier: Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
    ) {
        repeat(pageCount) {
            IndicatorSingleDot(isSelected = it == currentpage)
        }
    }
}

@Composable
fun IndicatorSingleDot(
    isSelected: Boolean
) {
    val width = animateDpAsState(targetValue = if (isSelected) 35.dp else 15.dp, label = "")
    Box(
        modifier = Modifier
            .padding(2.dp)
            .height(15.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(if (isSelected) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.tertiary)
    )
}

private  fun onBoardingIsFinished(context: MainActivity){
    val sharedPreferences = context.getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putBoolean("isFinished",true)
    editor.apply()
}


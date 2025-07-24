package com.example.hd_1.user_interface.auth.login

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.hd_1.R
import com.example.hd_1.navigation.Screens
import com.example.hd_1.ui.theme.sora_bold
import com.example.hd_1.ui.theme.sora_extraBold
import com.example.hd_1.ui.theme.sora_medium
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController
) {
    val context = LocalContext.current
    var userEmail by remember { mutableStateOf("") }
    var userPassword by remember { mutableStateOf("") }

    var passwordVisible by remember { mutableStateOf(false) }

    val viewModel = koinViewModel<LoginViewModel>()
    val uiState = viewModel.state.collectAsState()

    LaunchedEffect(key1 = uiState.value) {
        when(uiState.value) {
            LoginState.Error -> {
                Toast.makeText(context , "Sign In failed", Toast.LENGTH_SHORT).show()
            }
            LoginState.Success -> {
                navController.navigate(Screens.HomeScreenRoute)
            }
            else -> {}
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {

                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigate(Screens.OnboardingScreenRoute)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                            contentDescription = "backArrow",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 120.dp, bottom = 20.dp, start = 20.dp, end = 23.dp)
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(
                            color = MaterialTheme.colorScheme.background,
                            shape = MaterialTheme.shapes.medium
                        ),
                    contentAlignment = Alignment.TopCenter
                ) {
                    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animation_signup))
                    LottieAnimation(
                        composition = composition,
                        iterations = LottieConstants.IterateForever,
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(200.dp)
                    )
                }
                Spacer(modifier = Modifier.padding(10.dp))
                Row {
                    Text(
                        text = "Welcome back to ",
                        fontFamily = sora_extraBold,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = "GlitchCore",
                        fontFamily = sora_extraBold,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.secondary,
                        style = MaterialTheme.typography.titleLarge
                    )
                }
                Spacer(modifier = Modifier.padding(3.dp))
                Text(
                    text = "Please enter your login information",
                    fontFamily = sora_medium,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.tertiary,
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.padding(8.dp))
                OutlinedTextField(
                    value = userEmail,
                    onValueChange = {
                        userEmail = it
                    },
                    label = {
                        Text(
                            text = "Enter your Email",
                            fontFamily = sora_extraBold,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.secondary,
                        cursorColor = MaterialTheme.colorScheme.onPrimary,
                        focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.tertiary
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    ),
                    singleLine = true,
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.padding(3.dp))
                OutlinedTextField(
                    value = userPassword,
                    onValueChange = {
                        userPassword = it
                    },
                    label = {
                        Text(
                            text = "Enter your password",
                            fontFamily = sora_extraBold,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.secondary,
                        cursorColor = MaterialTheme.colorScheme.onPrimary,
                        focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.tertiary
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    ),
                    visualTransformation =  if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        val image = if (passwordVisible) painterResource(R.drawable.visibility_24dp_e3e3e3_fill0_wght400_grad0_opsz24) else painterResource(R.drawable.visibility_off_24dp_e3e3e3_fill0_wght400_grad0_opsz24)
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(painter = image, contentDescription = if (passwordVisible) "Hide password" else "Show password")
                        }
                    },
                    singleLine = true,
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.padding(1.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    Button(
                        onClick = {
                            navController.navigate(Screens.ForgotPasswordRoute)
                        },
                        colors = ButtonDefaults.textButtonColors(
                            containerColor = Color.Transparent
                        )
                    ) {
                        Text(
                            text = "forgot password?",
                            fontFamily = sora_bold,
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.tertiary,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                }
                OutlinedButton(
                    onClick = {
                        viewModel.logIn(userEmail , userPassword)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary
                    ),
                    enabled = userEmail.isNotEmpty() && userPassword.isNotEmpty() && (uiState.value == LoginState.Nothing || uiState.value == LoginState.Error),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text(
                        text = "Login",
                        fontFamily = sora_extraBold,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.padding(2.dp))
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Don't have a account?",
                        fontFamily = sora_medium,
                        color = MaterialTheme.colorScheme.tertiary,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = {
                            navController.navigate(Screens.LogUpScreenRoute)
                        },
                        colors = ButtonDefaults.textButtonColors(
                            containerColor = Color.Transparent
                        )
                    ) {
                        Text(
                            text = "Sign Up",
                            fontFamily = sora_bold,
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.secondary,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                }
            }
        }
    }
}
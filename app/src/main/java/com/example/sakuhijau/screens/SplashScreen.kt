package com.example.sakuhijau.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        // Using a placeholder logo from Pexels
        Image(
            painter = rememberAsyncImagePainter(
                model = "https://images.pexels.com/photos/5748726/pexels-photo-5748726.jpeg"
            ),
            contentDescription = "SakuHijau Logo",
            modifier = Modifier.size(200.dp),
            contentScale = ContentScale.Fit
        )

        // Auto-navigate to register screen after delay
        LaunchedEffect(key1 = true) {
            delay(2000) // 2 seconds delay
            navController.navigate("registerName") {
                popUpTo("splash") { inclusive = true }
            }
        }
    }
}

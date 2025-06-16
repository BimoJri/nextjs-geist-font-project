package com.example.sakuhijau

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sakuhijau.ui.theme.SakuHijauTheme
import com.example.sakuhijau.screens.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SakuHijauTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SakuHijauApp()
                }
            }
        }
    }
}

@Composable
fun SakuHijauApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") { SplashScreen(navController) }
        composable("registerName") { RegisterNameScreen(navController) }
        composable("registerPhoto") { RegisterPhotoScreen(navController) }
        composable("otpVerification") { OTPVerificationScreen(navController) }
        composable("registerCredentials") { RegisterCredentialsScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("dashboard") { DashboardScreen(navController) }
        composable("settings") { SettingsScreen(navController) }
        composable("rewardHistory") { RewardHistoryScreen(navController) }
        composable("withdraw") { WithdrawScreen(navController) }
        composable("confirmation") { ConfirmationScreen(navController) }
        composable("profile") { ProfileScreen(navController) }
        composable("editProfile") { EditProfileScreen(navController) }
        composable("passwordChange") { PasswordChangeScreen(navController) }
        composable("pendingTransactions") { PendingTransactionsScreen(navController) }
        composable("completedHistory") { CompletedHistoryScreen(navController) }
    }
}

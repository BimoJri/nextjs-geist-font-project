package com.example.sakuhijau.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OTPVerificationScreen(navController: NavController) {
    var otpValue by remember { mutableStateOf(TextFieldValue()) }
    var otpError by remember { mutableStateOf<String?>(null) }
    var isResendEnabled by remember { mutableStateOf(false) }
    var countdown by remember { mutableStateOf(30) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        while (countdown > 0) {
            delay(1000)
            countdown--
        }
        isResendEnabled = true
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Verify Phone") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Text("<") // Placeholder for back arrow
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Enter Verification Code",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "We've sent a verification code to your phone number",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            // OTP Input Field
            OutlinedTextField(
                value = otpValue,
                onValueChange = { 
                    if (it.text.length <= 6) {
                        otpValue = it
                        otpError = null
                    }
                },
                label = { Text("Enter 6-digit code") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                isError = otpError != null,
                supportingText = otpError?.let { { Text(it) } },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    focusedLabelColor = MaterialTheme.colorScheme.primary
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Resend Code Button with Timer
            TextButton(
                onClick = {
                    if (isResendEnabled) {
                        scope.launch {
                            isResendEnabled = false
                            countdown = 30
                            while (countdown > 0) {
                                delay(1000)
                                countdown--
                            }
                            isResendEnabled = true
                        }
                    }
                },
                enabled = isResendEnabled
            ) {
                Text(
                    if (isResendEnabled) "Resend Code" 
                    else "Resend code in ${countdown}s"
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Verify Button
            Button(
                onClick = {
                    when {
                        otpValue.text.length != 6 -> {
                            otpError = "Please enter a 6-digit code"
                        }
                        !otpValue.text.matches(Regex("^[0-9]{6}$")) -> {
                            otpError = "Invalid verification code"
                        }
                        else -> {
                            navController.navigate("registerCredentials")
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text("Verify")
            }
        }
    }
}

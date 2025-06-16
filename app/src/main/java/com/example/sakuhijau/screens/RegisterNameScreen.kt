package com.example.sakuhijau.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterNameScreen(navController: NavController) {
    var firstName by remember { mutableStateOf(TextFieldValue()) }
    var lastName by remember { mutableStateOf(TextFieldValue()) }
    var phone by remember { mutableStateOf(TextFieldValue()) }
    
    var firstNameError by remember { mutableStateOf<String?>(null) }
    var lastNameError by remember { mutableStateOf<String?>(null) }
    var phoneError by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Register") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
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
                text = "Let's get started!",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
            
            Text(
                text = "Please enter your details below",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
            )
            
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = firstName,
                onValueChange = {
                    firstName = it
                    firstNameError = null
                },
                label = { Text("First Name") },
                isError = firstNameError != null,
                supportingText = firstNameError?.let { { Text(it) } },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    focusedLabelColor = MaterialTheme.colorScheme.primary
                )
            )

            OutlinedTextField(
                value = lastName,
                onValueChange = {
                    lastName = it
                    lastNameError = null
                },
                label = { Text("Last Name") },
                isError = lastNameError != null,
                supportingText = lastNameError?.let { { Text(it) } },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    focusedLabelColor = MaterialTheme.colorScheme.primary
                )
            )

            OutlinedTextField(
                value = phone,
                onValueChange = {
                    phone = it
                    phoneError = null
                },
                label = { Text("Phone Number") },
                isError = phoneError != null,
                supportingText = phoneError?.let { { Text(it) } },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    focusedLabelColor = MaterialTheme.colorScheme.primary
                )
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    when {
                        firstName.text.isBlank() -> {
                            firstNameError = "First name is required"
                        }
                        lastName.text.isBlank() -> {
                            lastNameError = "Last name is required"
                        }
                        phone.text.isBlank() -> {
                            phoneError = "Phone number is required"
                        }
                        !phone.text.matches(Regex("^[0-9]{10,}$")) -> {
                            phoneError = "Please enter a valid phone number"
                        }
                        else -> {
                            navController.navigate("registerPhoto")
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
                Text("Next")
            }
        }
    }
}

package com.example.sakuhijau.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(navController: NavController) {
    var firstName by remember { mutableStateOf(TextFieldValue("John")) }
    var lastName by remember { mutableStateOf(TextFieldValue("Doe")) }
    var email by remember { mutableStateOf(TextFieldValue("john.doe@example.com")) }
    var phone by remember { mutableStateOf(TextFieldValue("+62 812-3456-7890")) }
    var showPhotoOptions by remember { mutableStateOf(false) }
    
    var firstNameError by remember { mutableStateOf<String?>(null) }
    var lastNameError by remember { mutableStateOf<String?>(null) }
    var emailError by remember { mutableStateOf<String?>(null) }
    var phoneError by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit Profile") },
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            // Profile Picture
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.secondaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        model = "https://images.pexels.com/photos/415829/pexels-photo-415829.jpeg"
                    ),
                    contentDescription = "Profile Picture",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            // Change Photo Button
            TextButton(
                onClick = { showPhotoOptions = true },
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Text("Change Photo")
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Profile Form
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

            Spacer(modifier = Modifier.height(16.dp))

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

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    emailError = null
                },
                label = { Text("Email") },
                isError = emailError != null,
                supportingText = emailError?.let { { Text(it) } },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    focusedLabelColor = MaterialTheme.colorScheme.primary
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

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

            // Save Button
            Button(
                onClick = {
                    when {
                        firstName.text.isBlank() -> {
                            firstNameError = "First name is required"
                        }
                        lastName.text.isBlank() -> {
                            lastNameError = "Last name is required"
                        }
                        !email.text.matches(Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) -> {
                            emailError = "Please enter a valid email address"
                        }
                        !phone.text.matches(Regex("^\\+?[0-9\\s-]{10,}$")) -> {
                            phoneError = "Please enter a valid phone number"
                        }
                        else -> {
                            navController.navigateUp()
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
                Text("Save Changes")
            }
        }

        // Photo Options Dialog
        if (showPhotoOptions) {
            AlertDialog(
                onDismissRequest = { showPhotoOptions = false },
                title = { Text("Change Profile Photo") },
                text = {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        TextButton(
                            onClick = {
                                showPhotoOptions = false
                                // Handle take photo
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Take Photo")
                        }
                        TextButton(
                            onClick = {
                                showPhotoOptions = false
                                // Handle choose from gallery
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Choose from Gallery")
                        }
                        TextButton(
                            onClick = {
                                showPhotoOptions = false
                                // Handle remove photo
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                "Remove Photo",
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                },
                confirmButton = {
                    TextButton(onClick = { showPhotoOptions = false }) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}

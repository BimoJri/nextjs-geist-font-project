package com.example.sakuhijau.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.text.NumberFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RewardHistoryScreen(navController: NavController) {
    // Dummy data for reward history
    val rewardHistory = remember {
        listOf(
            RewardItem("Plastic Bottle Recycling", 50, "2023-12-15", "Earned"),
            RewardItem("Paper Waste Collection", 30, "2023-12-14", "Earned"),
            RewardItem("Withdrawal to Wallet", -100, "2023-12-13", "Withdrawn"),
            RewardItem("Glass Recycling", 75, "2023-12-12", "Earned"),
            RewardItem("Metal Waste Collection", 60, "2023-12-11", "Earned"),
            RewardItem("Withdrawal to Wallet", -150, "2023-12-10", "Withdrawn")
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Reward History") },
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
        ) {
            // Total Points Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Total Points",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Text(
                        text = "1,250",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }

            // History List
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(rewardHistory) { reward ->
                    RewardHistoryItem(reward)
                }
            }
        }
    }
}

@Composable
private fun RewardHistoryItem(reward: RewardItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = reward.description,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = reward.date,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }
            
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "${if (reward.points > 0) "+" else ""}${reward.points}",
                    style = MaterialTheme.typography.titleMedium,
                    color = if (reward.points > 0) 
                        MaterialTheme.colorScheme.primary 
                    else 
                        MaterialTheme.colorScheme.error
                )
                Text(
                    text = reward.type,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }
        }
    }
}

private data class RewardItem(
    val description: String,
    val points: Int,
    val date: String,
    val type: String
)

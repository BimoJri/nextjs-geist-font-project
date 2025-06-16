package com.example.sakuhijau.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.text.NumberFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompletedHistoryScreen(navController: NavController) {
    // Dummy data for completed transactions
    val completedTransactions = remember {
        listOf(
            CompletedTransaction(
                id = "TRX-001",
                type = "Plastic Bottle",
                amount = "5.2 kg",
                points = 104,
                date = "2023-12-10 14:30",
                status = "Completed",
                location = "Drop Point A"
            ),
            CompletedTransaction(
                id = "TRX-002",
                type = "Paper Waste",
                amount = "3.8 kg",
                points = 76,
                date = "2023-12-09 15:45",
                status = "Completed",
                location = "Drop Point B"
            ),
            CompletedTransaction(
                id = "TRX-003",
                type = "Glass Bottles",
                amount = "4.5 kg",
                points = 90,
                date = "2023-12-08 16:20",
                status = "Completed",
                location = "Drop Point C"
            ),
            CompletedTransaction(
                id = "TRX-004",
                type = "Metal Waste",
                amount = "2.3 kg",
                points = 115,
                date = "2023-12-07 10:15",
                status = "Completed",
                location = "Drop Point A"
            )
        )
    }

    var selectedFilter by remember { mutableStateOf("All") }
    val filters = listOf("All", "Plastic", "Paper", "Glass", "Metal")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Transaction History") },
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
            // Filters
            ScrollableTabRow(
                selectedTabIndex = filters.indexOf(selectedFilter),
                modifier = Modifier.fillMaxWidth(),
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.primary
            ) {
                filters.forEach { filter ->
                    Tab(
                        selected = selectedFilter == filter,
                        onClick = { selectedFilter = filter },
                        text = { Text(filter) }
                    )
                }
            }

            if (completedTransactions.isEmpty()) {
                // Empty State
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "No Transaction History",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "Your completed transactions will appear here",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(completedTransactions.filter {
                        selectedFilter == "All" || it.type.contains(selectedFilter)
                    }) { transaction ->
                        CompletedTransactionCard(transaction)
                    }
                }
            }
        }
    }
}

@Composable
private fun CompletedTransactionCard(transaction: CompletedTransaction) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Transaction ID and Status
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = transaction.id,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = transaction.status,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Transaction Details
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                TransactionDetailRow("Type", transaction.type)
                TransactionDetailRow("Amount", transaction.amount)
                TransactionDetailRow(
                    "Points Earned",
                    "${transaction.points} points",
                    MaterialTheme.colorScheme.primary
                )
                TransactionDetailRow("Location", transaction.location)
                TransactionDetailRow("Date", transaction.date)
            }
        }
    }
}

@Composable
private fun TransactionDetailRow(
    label: String,
    value: String,
    valueColor: androidx.compose.ui.graphics.Color = MaterialTheme.colorScheme.onSurface
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = valueColor
        )
    }
}

private data class CompletedTransaction(
    val id: String,
    val type: String,
    val amount: String,
    val points: Int,
    val date: String,
    val status: String,
    val location: String
)

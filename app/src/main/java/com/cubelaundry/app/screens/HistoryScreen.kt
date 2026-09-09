package com.cubelaundry.app.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.cubelaundry.app.models.HistoryOrder

@Composable
fun HistoryScreen(navController: NavController) {
    // Mock data – replace with actual API call later
    val mockOrders = listOf(
        HistoryOrder("CL-000001", "John Doe", 1500, "2026-09-01", "Delivered"),
        HistoryOrder("CL-000002", "Jane Smith", 2200, "2026-08-28", "Pending")
    )

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("My Orders", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(bottom = 16.dp))
        LazyColumn {
            items(mockOrders) { order ->
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    onClick = { navController.navigate("invoice/${order.invoice_number}") }
                ) {
                    Row(modifier = Modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                        Column {
                            Text(order.invoice_number, fontWeight = FontWeight.Bold)
                            Text(order.created_at, style = MaterialTheme.typography.bodySmall)
                        }
                        Text("Rs. ${order.grand_total}", color = MaterialTheme.colorScheme.primary)
                    }
                }
            }
        }
    }
}

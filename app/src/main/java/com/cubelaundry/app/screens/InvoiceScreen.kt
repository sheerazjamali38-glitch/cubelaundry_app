package com.cubelaundry.app.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.cubelaundry.app.viewmodels.InvoiceViewModel

@Composable
fun InvoiceScreen(navController: NavController, invoiceNumber: String, viewModel: InvoiceViewModel = viewModel()) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(invoiceNumber) { viewModel.loadInvoice(invoiceNumber) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Invoice #$invoiceNumber", style = MaterialTheme.typography.headlineMedium)

        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
        } else if (state.error != null) {
            Text(state.error!!, color = MaterialTheme.colorScheme.error)
        } else if (state.invoice != null) {
            val order = state.invoice!!.order
            Card(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Customer: ${order?.customer_name}")
                    Text("Mobile: ${order?.mobile_number}")
                    Text("Address: ${order?.address}")
                    Text("Total: Rs. ${order?.grand_total}")
                    Divider(modifier = Modifier.padding(vertical = 8.dp))
                    Text("Items:", style = MaterialTheme.typography.titleSmall)
                    state.invoice!!.items?.forEach {
                        Text("- ${it.item_name} x${it.quantity} = Rs. ${it.subtotal}")
                    }
                }
            }
        } else {
            Text("No invoice data.")
        }
    }
}

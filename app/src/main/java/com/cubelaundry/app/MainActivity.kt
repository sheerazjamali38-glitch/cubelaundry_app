package com.cubelaundry.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cubelaundry.app.screens.*
import com.cubelaundry.app.ui.theme.CubeLaundryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CubeLaundryTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") { HomeScreen(navController) }
                        composable("order") { OrderScreen(navController) }
                        composable("history") { HistoryScreen(navController) }
                        composable("invoice/{invoiceNumber}") { backStackEntry ->
                            val invoiceNumber = backStackEntry.arguments?.getString("invoiceNumber") ?: ""
                            InvoiceScreen(navController, invoiceNumber)
                        }
                    }
                }
            }
        }
    }
}

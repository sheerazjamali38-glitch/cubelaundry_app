package com.cubelaundry.app.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.cubelaundry.app.R
import com.cubelaundry.app.ui.theme.*

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painterResource(R.drawable.logo), contentDescription = "Logo", modifier = Modifier.size(120.dp))
        Spacer(Modifier.height(16.dp))
        Text("Cube Laundry", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Navy)
        Text("Fresh • Fast • Reliable", fontSize = 14.sp, color = Sub)
        Spacer(Modifier.height(40.dp))
        Button(onClick = { navController.navigate("order") }, modifier = Modifier.fillMaxWidth()) {
            Text("Place Order", fontSize = 16.sp)
        }
        Spacer(Modifier.height(12.dp))
        Button(onClick = { navController.navigate("history") }, modifier = Modifier.fillMaxWidth()) {
            Text("My Orders", fontSize = 16.sp)
        }
        Spacer(Modifier.height(12.dp))
        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/923478164692"))
                navController.context.startActivity(intent)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Contact on WhatsApp", fontSize = 16.sp)
        }
    }
}

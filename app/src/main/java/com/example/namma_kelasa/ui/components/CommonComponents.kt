package com.example.namma_kelasa.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.automirrored.filled.ListAlt
import androidx.compose.material.icons.automirrored.outlined.Chat
import androidx.compose.material.icons.automirrored.outlined.ListAlt
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.namma_kelasa.ui.theme.PrimaryIndigo

@Composable
fun AppBottomNavigation(currentScreen: String, onNavigate: (String) -> Unit) {
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp
    ) {
        NavigationBarItem(
            selected = currentScreen == "home",
            onClick = { onNavigate("home") },
            icon = { 
                Icon(
                    imageVector = if (currentScreen == "home") Icons.Default.Home else Icons.Outlined.Home, 
                    contentDescription = "Home"
                ) 
            },
            label = { Text("Home") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = PrimaryIndigo,
                selectedTextColor = PrimaryIndigo,
                indicatorColor = Color(0xFFF3E5F5)
            )
        )
        NavigationBarItem(
            selected = currentScreen == "jobs",
            onClick = { onNavigate("jobs") },
            icon = { 
                Icon(
                    imageVector = if (currentScreen == "jobs") Icons.AutoMirrored.Filled.ListAlt else Icons.AutoMirrored.Outlined.ListAlt, 
                    contentDescription = "Jobs"
                ) 
            },
            label = { Text("Jobs") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = PrimaryIndigo,
                selectedTextColor = PrimaryIndigo,
                indicatorColor = Color(0xFFF3E5F5)
            )
        )
        NavigationBarItem(
            selected = currentScreen == "chats",
            onClick = { onNavigate("chats") },
            icon = { 
                Icon(
                    imageVector = if (currentScreen == "chats") Icons.AutoMirrored.Filled.Chat else Icons.AutoMirrored.Outlined.Chat, 
                    contentDescription = "Chats"
                ) 
            },
            label = { Text("Chats") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = PrimaryIndigo,
                selectedTextColor = PrimaryIndigo,
                indicatorColor = Color(0xFFF3E5F5)
            )
        )
        NavigationBarItem(
            selected = currentScreen == "profile",
            onClick = { onNavigate("profile") },
            icon = { 
                Icon(
                    imageVector = if (currentScreen == "profile") Icons.Default.Person else Icons.Outlined.Person, 
                    contentDescription = "Profile"
                ) 
            },
            label = { Text("Profile") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = PrimaryIndigo,
                selectedTextColor = PrimaryIndigo,
                indicatorColor = Color(0xFFF3E5F5)
            )
        )
    }
}

package com.example.namma_kelasa.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.namma_kelasa.ui.components.AppBottomNavigation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(onNavigate: (String) -> Unit) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Chats", fontSize = 20.sp, fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.White)
            )
        },
        bottomBar = { AppBottomNavigation(currentScreen = "chats", onNavigate = onNavigate) }
    ) { padding ->
        val mockChats = listOf(
            ChatSummary("Ramesh Painter", "When can you start?", "10:30 AM", 2),
            ChatSummary("Mahesh Painter", "I can come tomorrow.", "Yesterday", 0),
            ChatSummary("Suresh Electrician", "Price is fixed.", "Mon", 0)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFFF9FAFB))
        ) {
            items(mockChats) { chat ->
                ChatItem(chat)
                HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp), thickness = 0.5.dp, color = Color.LightGray)
            }
        }
    }
}

data class ChatSummary(val name: String, val lastMessage: String, val time: String, val unreadCount: Int)

@Composable
fun ChatItem(chat: ChatSummary) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(Color.LightGray)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = chat.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = chat.lastMessage, color = Color.Gray, fontSize = 14.sp, maxLines = 1)
        }
        Column(horizontalAlignment = Alignment.End) {
            Text(text = chat.time, fontSize = 12.sp, color = Color.Gray)
            if (chat.unreadCount > 0) {
                Badge(containerColor = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(top = 4.dp)) {
                    Text(text = chat.unreadCount.toString(), color = Color.White)
                }
            }
        }
    }
}

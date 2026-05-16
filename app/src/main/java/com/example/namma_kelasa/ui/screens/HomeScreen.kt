package com.example.namma_kelasa.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.namma_kelasa.data.Worker
import com.example.namma_kelasa.ui.components.AppBottomNavigation
import com.example.namma_kelasa.ui.theme.PrimaryIndigo
import com.example.namma_kelasa.ui.theme.RatingYellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onWorkerClick: (Worker) -> Unit,
    onSearchClick: () -> Unit,
    onNavigate: (String) -> Unit
) {
    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .statusBarsPadding()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.LocationOn,
                        contentDescription = null,
                        tint = PrimaryIndigo,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Bengaluru", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Icon(Icons.Default.KeyboardArrowDown, contentDescription = null, modifier = Modifier.size(20.dp))
                }
                Spacer(modifier = Modifier.height(12.dp))
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .clickable { onSearchClick() },
                    shape = RoundedCornerShape(24.dp),
                    color = Color(0xFFF3F4F6)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.Search, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(12.dp))
                        Text("Search by skill or location", color = Color.Gray, fontSize = 14.sp)
                    }
                }
            }
        },
        bottomBar = { AppBottomNavigation(currentScreen = "home", onNavigate = onNavigate) }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFFF9FAFB))
        ) {
            item {
                SectionHeader(title = "Popular Skills", onActionClick = { onSearchClick() })
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    val skills = listOf(
                        SkillItem("Painting", Icons.Default.FormatPaint),
                        SkillItem("Tiling", Icons.Default.GridOn),
                        SkillItem("Plumbing", Icons.Default.WaterDrop),
                        SkillItem("Electrical", Icons.Default.FlashOn),
                        SkillItem("More", Icons.Default.MoreHoriz)
                    )
                    items(skills) { skill ->
                        SkillCard(skill)
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                SectionHeader(title = "Nearby Workers", onActionClick = {})
            }

            val workers = listOf(
                Worker("1", "Ramesh Painter", "Painter", "Koramangala, Bengaluru", 4.8, 24, "₹800/day"),
                Worker("2", "Mahesh Painter", "Painter", "Koramangala, Bengaluru", 4.5, 12, "₹750/day")
            )

            items(workers) { worker ->
                WorkerListItem(
                    worker = worker,
                    onClick = { onWorkerClick(worker) },
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
                )
            }
            
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun SectionHeader(title: String, onActionClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        TextButton(onClick = onActionClick) {
            Text("View all", color = PrimaryIndigo, fontWeight = FontWeight.SemiBold)
        }
    }
}

data class SkillItem(val name: String, val icon: ImageVector)

@Composable
fun SkillCard(skill: SkillItem) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFEDE7F6)),
            contentAlignment = Alignment.Center
        ) {
            Icon(skill.icon, contentDescription = null, tint = PrimaryIndigo, modifier = Modifier.size(28.dp))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(skill.name, fontSize = 12.sp, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun WorkerListItem(worker: Worker, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        color = Color.White,
        tonalElevation = 1.dp,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(worker.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(worker.skill, fontSize = 14.sp, color = Color.Gray)
                Text(worker.location, fontSize = 12.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(4.dp))
                Text(worker.dailyRate, fontWeight = FontWeight.Bold, color = PrimaryIndigo, fontSize = 14.sp)
            }
            Column(horizontalAlignment = Alignment.End) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Star, contentDescription = null, tint = RatingYellow, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(2.dp))
                    Text("${worker.rating} (${worker.reviewCount})", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

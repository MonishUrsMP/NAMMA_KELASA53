package com.example.namma_kelasa.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.namma_kelasa.data.JobEnquiry
import com.example.namma_kelasa.ui.components.AppBottomNavigation
import com.example.namma_kelasa.ui.theme.PrimaryIndigo
import com.example.namma_kelasa.ui.theme.StatusGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyJobsScreen(onNavigate: (String) -> Unit) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Enquiries", "Hired Jobs")

    Scaffold(
        topBar = {
            Column(modifier = Modifier.background(Color.White)) {
                CenterAlignedTopAppBar(
                    title = { Text("My Jobs / Enquiries", fontSize = 20.sp, fontWeight = FontWeight.Bold) },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.White
                    )
                )
                TabRow(
                    selectedTabIndex = selectedTab,
                    containerColor = Color.White,
                    contentColor = PrimaryIndigo,
                    indicator = { tabPositions ->
                        if (selectedTab < tabPositions.size) {
                            TabRowDefaults.SecondaryIndicator(
                                modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                                color = PrimaryIndigo
                            )
                        }
                    },
                    divider = {
                        HorizontalDivider(color = Color.LightGray.copy(alpha = 0.5f))
                    }
                ) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            selected = selectedTab == index,
                            onClick = { selectedTab = index },
                            text = { 
                                Text(
                                    text = title,
                                    fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal,
                                    fontSize = 15.sp
                                ) 
                            },
                            selectedContentColor = PrimaryIndigo,
                            unselectedContentColor = Color.Gray
                        )
                    }
                }
            }
        },
        bottomBar = { AppBottomNavigation(currentScreen = "jobs", onNavigate = onNavigate) }
    ) { padding ->
        val allEnquiries = listOf(
            JobEnquiry("1", "House Painting", "Koramangala, Bengaluru", "Today, 10:30 AM", "New Enquiry"),
            JobEnquiry("2", "Wall Painting", "HSR Layout, Bengaluru", "Today, 09:15 AM", "New Enquiry"),
            JobEnquiry("3", "Office Painting", "Indiranagar, Bengaluru", "Yesterday, 04:45 PM", "Hired", isHired = true)
        )
        
        val filteredEnquiries = if (selectedTab == 0) {
            allEnquiries.filter { !it.isHired }
        } else {
            allEnquiries.filter { it.isHired }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFFF9FAFB))
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item { Spacer(modifier = Modifier.height(8.dp)) }
            items(filteredEnquiries) { enquiry ->
                JobItem(enquiry)
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@Composable
fun JobItem(enquiry: JobEnquiry) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Color.White,
        shadowElevation = 1.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = enquiry.title, 
                        fontWeight = FontWeight.Bold, 
                        fontSize = 17.sp,
                        color = Color(0xFF1C1B1F)
                    )
                    Text(
                        text = if (enquiry.isHired) "Hired" else "New Enquiry",
                        color = if (enquiry.isHired) StatusGreen else PrimaryIndigo,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = enquiry.location, 
                    fontSize = 14.sp, 
                    color = Color.Gray,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = enquiry.time, 
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

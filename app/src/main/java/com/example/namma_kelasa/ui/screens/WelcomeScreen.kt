package com.example.namma_kelasa.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AllInclusive
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.namma_kelasa.ui.theme.PrimaryIndigo

@Composable
fun WelcomeScreen(onContinue: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Background illustration placeholder
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.35f)
                .align(Alignment.BottomCenter)
        ) {
            Icon(
                painter = painterResource(id = android.R.drawable.ic_menu_gallery),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(0.05f),
                tint = PrimaryIndigo
            )
            
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.White, Color.Transparent),
                            startY = 0f,
                            endY = 300f
                        )
                    )
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .statusBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top branding
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.AllInclusive,
                    contentDescription = "MindMatrix",
                    modifier = Modifier.size(24.dp),
                    tint = PrimaryIndigo
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "MindMatrix",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = PrimaryIndigo
                )
            }

            Spacer(modifier = Modifier.weight(0.1f))

            // Rainbow Logo and Title
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                RainbowLogo(modifier = Modifier.size(120.dp))
                
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = "Namma-",
                    fontSize = 48.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = PrimaryIndigo,
                    textAlign = TextAlign.Center,
                    lineHeight = 44.sp
                )
                Text(
                    text = "Kelsa",
                    fontSize = 48.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = PrimaryIndigo,
                    textAlign = TextAlign.Center,
                    lineHeight = 44.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Dignity-First Labor\nMarketplace",
                    fontSize = 18.sp,
                    color = Color.DarkGray,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 24.sp
                )
            }

            Spacer(modifier = Modifier.weight(0.4f))

            // Bottom Buttons
            Button(
                onClick = onContinue,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryIndigo)
            ) {
                Text("Get Started", fontSize = 18.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            TextButton(onClick = {}) {
                Text("Select Language", color = PrimaryIndigo, fontWeight = FontWeight.Medium)
            }
            
            Spacer(modifier = Modifier.navigationBarsPadding())
        }
    }
}

@Composable
fun RainbowLogo(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val strokeWidth = 10.dp.toPx()
        val size = size.minDimension
        
        drawArc(
            color = Color(0xFFD32F2F),
            startAngle = 180f,
            sweepAngle = 180f,
            useCenter = false,
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round),
            size = androidx.compose.ui.geometry.Size(size, size),
            topLeft = androidx.compose.ui.geometry.Offset(0f, size / 4)
        )
        
        drawArc(
            color = Color(0xFF1976D2),
            startAngle = 180f,
            sweepAngle = 180f,
            useCenter = false,
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round),
            size = androidx.compose.ui.geometry.Size(size * 0.75f, size * 0.75f),
            topLeft = androidx.compose.ui.geometry.Offset(size * 0.125f, size / 4 + size * 0.125f)
        )
        
        drawArc(
            color = Color(0xFF7B1FA2),
            startAngle = 180f,
            sweepAngle = 180f,
            useCenter = false,
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round),
            size = androidx.compose.ui.geometry.Size(size * 0.5f, size * 0.5f),
            topLeft = androidx.compose.ui.geometry.Offset(size * 0.25f, size / 4 + size * 0.25f)
        )
    }
}

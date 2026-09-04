package com.krithi.frauddetector.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.krithi.frauddetector.ui.components.FeatureCard
import com.krithi.frauddetector.ui.components.StatusCard

@Composable
fun HomeScreen(
    navController: NavController,
    darkMode: Boolean,
    modifier: Modifier = Modifier
){
    val isDarkMode = darkMode

    val backgroundColor = if (isDarkMode) {
        Color(0xFF080B14)
    } else {
        MaterialTheme.colorScheme.background
    }

    val primaryText = if (isDarkMode) {
        Color.White
    } else {
        MaterialTheme.colorScheme.onBackground
    }

    val secondaryText = if (isDarkMode) {
        Color(0xFF9EA6BD)
    } else {
        MaterialTheme.colorScheme.onSurfaceVariant
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 22.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        // ─────────────────────────────────────────
        // HERO SECTION
        // ─────────────────────────────────────────

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(26.dp))
                .background(
                    brush = if (isDarkMode) {
                        Brush.linearGradient(
                            colors = listOf(
                                Color(0xFF1D1A38),
                                Color(0xFF151A2A),
                                Color(0xFF111522)
                            )
                        )
                    } else {
                        Brush.linearGradient(
                            colors = listOf(
                                Color(0xFFF0EEFF),
                                Color(0xFFF8F7FF),
                                Color(0xFFFFFFFF)
                            )
                        )
                    }
                )
                .padding(22.dp)
        ) {

            Column(
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(
                        modifier = Modifier
                            .size(58.dp)
                            .clip(RoundedCornerShape(18.dp))
                            .background(
                                Color(0xFF8B7CFF).copy(alpha = 0.15f)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Security,
                            contentDescription = "SafeLens",
                            tint = Color(0xFF8B7CFF),
                            modifier = Modifier.size(32.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column {
                        Text(
                            text = "SafeLens",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            color = primaryText
                        )

                        Text(
                            text = "See Beyond Scams",
                            style = MaterialTheme.typography.bodyMedium,
                            color = secondaryText
                        )
                    }
                }

                Text(
                    text = "Your first line of defense against digital scams.",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = primaryText
                )

                Text(
                    text = "Check suspicious phone numbers, websites, and messages before they put you at risk.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = secondaryText
                )

                // Protection badge
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50.dp))
                        .background(
                            Color(0xFF52D9A0).copy(alpha = 0.12f)
                        )
                        .padding(
                            horizontal = 12.dp,
                            vertical = 7.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(
                        modifier = Modifier
                            .size(7.dp)
                            .clip(RoundedCornerShape(50.dp))
                            .background(Color(0xFF52D9A0))
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "Protection Active",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF52D9A0)
                    )
                }
            }
        }

        // ─────────────────────────────────────────
        // STATUS
        // ─────────────────────────────────────────

        StatusCard()

        // ─────────────────────────────────────────
        // TOOLS HEADER
        // ─────────────────────────────────────────

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 4.dp,
                    start = 2.dp,
                    end = 2.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Security Tools",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = primaryText
                )

                Text(
                    text = "Choose what you want to check",
                    style = MaterialTheme.typography.bodySmall,
                    color = secondaryText
                )
            }

            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = "Security",
                tint = Color(0xFF8B7CFF),
                modifier = Modifier.size(22.dp)
            )
        }

        // ─────────────────────────────────────────
        // SECURITY TOOLS
        // ─────────────────────────────────────────

        FeatureCard(
            icon = Icons.Default.Call,
            title = "Check Phone Number",
            description = "Analyze a number for suspicious patterns",
            onClick = {
                navController.navigate("phone")
            }
        )

        FeatureCard(
            icon = Icons.Default.Language,
            title = "Scan Website",
            description = "Check a URL for known security threats",
            onClick = {
                navController.navigate("url")
            }
        )

        FeatureCard(
            icon = Icons.Default.Message,
            title = "Analyze SMS",
            description = "Detect scam and phishing indicators",
            onClick = {
                navController.navigate("sms")
            }
        )

        // ─────────────────────────────────────────
        // FOOTER
        // ─────────────────────────────────────────

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Stay alert. Think before you click.",
            style = MaterialTheme.typography.bodySmall,
            color = secondaryText,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(8.dp))
    }
}
package com.krithi.frauddetector.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.krithi.frauddetector.viewmodel.ScanViewModel

@Composable
fun HistoryScreen(
    scanViewModel: ScanViewModel
) {
    val history by scanViewModel.scanHistory.collectAsState()

    // Detect current theme
    val isDarkMode =
        MaterialTheme.colorScheme.background == Color(0xFF080B14)

    // Theme-aware colors
    val backgroundColor = if (isDarkMode) {
        Color(0xFF080B14)
    } else {
        MaterialTheme.colorScheme.background
    }

    val glassColor = if (isDarkMode) {
        Color(0xFF151A2A).copy(alpha = 0.88f)
    } else {
        MaterialTheme.colorScheme.surface
    }

    val glassBorder = if (isDarkMode) {
        Color(0xFF343B55).copy(alpha = 0.75f)
    } else {
        MaterialTheme.colorScheme.outline.copy(alpha = 0.35f)
    }

    val primaryText = if (isDarkMode) {
        Color.White
    } else {
        MaterialTheme.colorScheme.onSurface
    }

    val secondaryText = if (isDarkMode) {
        Color(0xFF9EA6BD)
    } else {
        MaterialTheme.colorScheme.onSurfaceVariant
    }

    val mutedText = if (isDarkMode) {
        Color(0xFF777F98)
    } else {
        MaterialTheme.colorScheme.onSurfaceVariant
    }

    val innerCardColor = if (isDarkMode) {
        Color(0xFF0D111D).copy(alpha = 0.78f)
    } else {
        MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f)
    }

    val innerBorder = if (isDarkMode) {
        Color(0xFF30364D)
    } else {
        MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
    }

    val purpleAccent = Color(0xFF8B7CFF)
    val greenAccent = Color(0xFF52D9A0)
    val yellowAccent = Color(0xFFFFC857)
    val redAccent = Color(0xFFFF5C68)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(
                start = 20.dp,
                top = 20.dp,
                end = 20.dp
            )
    ) {

        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.History,
                contentDescription = null,
                tint = purpleAccent
            )

            Spacer(
                modifier = Modifier.width(10.dp)
            )

            Text(
                text = "Scan History",
                style = MaterialTheme.typography.headlineLarge,
                color = primaryText
            )
        }

        Spacer(
            modifier = Modifier.height(18.dp)
        )

        // Activity summary card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = glassColor
            ),
            border = BorderStroke(
                1.dp,
                glassBorder
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Scan Activity",
                        style = MaterialTheme.typography.titleMedium,
                        color = primaryText
                    )

                    Spacer(
                        modifier = Modifier.height(3.dp)
                    )

                    Text(
                        text = "${history.size} scans saved",
                        style = MaterialTheme.typography.bodySmall,
                        color = mutedText
                    )
                }

                if (history.isNotEmpty()) {
                    Button(
                        onClick = {
                            scanViewModel.clearHistory()
                        },
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = redAccent.copy(alpha = 0.15f),
                            contentColor = redAccent
                        )
                    ) {
                        Text("Clear All")
                    }
                }
            }
        }

        Spacer(
            modifier = Modifier.height(18.dp)
        )

        if (history.isEmpty()) {

            // Empty state
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = glassColor
                ),
                border = BorderStroke(
                    1.dp,
                    glassBorder
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(28.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Icon(
                        imageVector = Icons.Default.History,
                        contentDescription = null,
                        tint = purpleAccent
                    )

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    Text(
                        text = "No Scan History",
                        style = MaterialTheme.typography.titleLarge,
                        color = primaryText
                    )

                    Spacer(
                        modifier = Modifier.height(6.dp)
                    )

                    Text(
                        text = "Your phone, URL, and SMS scans will appear here.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = secondaryText
                    )
                }
            }

        } else {

            // History list
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {

                items(
                    items = history,
                    key = { it.id }
                ) { scan ->

                    val resultText = scan.result

                    val isDangerous =
                        resultText.contains("🚨")

                    val isWarning =
                        resultText.contains("⚠️")

                    val resultAccent = when {
                        isDangerous -> redAccent
                        isWarning -> yellowAccent
                        else -> greenAccent
                    }

                    val scanIcon = when (scan.type) {
                        "Phone Number" -> Icons.Default.Call
                        "URL" -> Icons.Default.Language
                        "SMS" -> Icons.Default.Message
                        else -> Icons.Default.History
                    }

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = glassColor
                        ),
                        border = BorderStroke(
                            1.dp,
                            glassBorder
                        )
                    ) {

                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {

                            // Header row
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Icon(
                                    imageVector = scanIcon,
                                    contentDescription = null,
                                    tint = purpleAccent
                                )

                                Spacer(
                                    modifier = Modifier.width(10.dp)
                                )

                                Column(
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(
                                        text = scan.type,
                                        style = MaterialTheme.typography.titleMedium,
                                        color = primaryText
                                    )

                                    Text(
                                        text = "Scan result",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = mutedText
                                    )
                                }

                                IconButton(
                                    onClick = {
                                        scanViewModel.deleteScan(scan.id)
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Delete scan",
                                        tint = mutedText
                                    )
                                }
                            }

                            // Scanned input
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(14.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = innerCardColor
                                ),
                                border = BorderStroke(
                                    1.dp,
                                    innerBorder
                                )
                            ) {
                                Column(
                                    modifier = Modifier.padding(14.dp)
                                ) {

                                    Text(
                                        text = "SCANNED INPUT",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = mutedText
                                    )

                                    Spacer(
                                        modifier = Modifier.height(5.dp)
                                    )

                                    Text(
                                        text = scan.input,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = primaryText
                                    )
                                }
                            }

                            // Result
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Icon(
                                    imageVector = if (isDangerous || isWarning) {
                                        Icons.Default.Warning
                                    } else {
                                        Icons.Default.CheckCircle
                                    },
                                    contentDescription = null,
                                    tint = resultAccent
                                )

                                Spacer(
                                    modifier = Modifier.width(8.dp)
                                )

                                Text(
                                    text = resultText,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = resultAccent
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
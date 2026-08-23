package com.krithi.frauddetector.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.krithi.frauddetector.ui.components.FeatureCard
import com.krithi.frauddetector.ui.components.StatsCard
import com.krithi.frauddetector.ui.components.StatusCard

@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        // App Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = Icons.Default.Security,
                contentDescription = "SafeLens",
                tint = MaterialTheme.colorScheme.primary
            )

            Spacer(
                modifier = Modifier.width(10.dp)
            )

            Column {

                Text(
                    text = "SafeLens",
                    style = MaterialTheme.typography.headlineLarge
                )

                Text(
                    text = "See Beyond Scams",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        Spacer(
            modifier = Modifier.height(4.dp)
        )

        // Security Status
        StatusCard()

        // Statistics
        StatsCard()

        Text(
            text = "Security Tools",
            style = MaterialTheme.typography.titleLarge
        )

        // Phone Scanner
        FeatureCard(
            icon = Icons.Filled.Call,
            title = "Check Phone Number",
            description = "Analyze phone numbers for suspicious patterns",
            onClick = {
                navController.navigate("phone")
            }
        )

        // URL Scanner
        FeatureCard(
            icon = Icons.Filled.Language,
            title = "Scan URL",
            description = "Check websites for known security threats",
            onClick = {
                navController.navigate("url")
            }
        )

        // SMS Analyzer
        FeatureCard(
            icon = Icons.Filled.Message,
            title = "Analyze SMS",
            description = "Detect common scam and phishing indicators",
            onClick = {
                navController.navigate("sms")
            }
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "Stay alert. Think before you click.",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}
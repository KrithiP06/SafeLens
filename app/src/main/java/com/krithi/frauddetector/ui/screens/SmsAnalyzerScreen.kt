package com.krithi.frauddetector.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.krithi.frauddetector.ui.components.ScreenHeader
import com.krithi.frauddetector.viewmodel.ScanViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SmsAnalyzerScreen(
    navController: NavController,
    scanViewModel: ScanViewModel
) {

    var sms by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    var scanning by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        ScreenHeader(
            title = "Analyze SMS",
            onBack = {
                navController.popBackStack()
            }
        )

        Text(
            text = "Check a message for common fraud and phishing indicators.",
            style = MaterialTheme.typography.bodyMedium
        )

        OutlinedTextField(
            value = sms,
            onValueChange = { sms = it },
            label = {
                Text("Paste SMS")
            },
            placeholder = {
                Text("Paste the message you want to analyze...")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        )

        Button(
            enabled = !scanning && sms.isNotBlank(),
            onClick = {

                scope.launch {

                    scanning = true
                    result = ""

                    delay(1500)

                    val lowerSms = sms.lowercase()

                    val suspiciousKeywords = listOf(
                        "otp",
                        "pin",
                        "password",
                        "verify your account",
                        "account suspended",
                        "account blocked",
                        "click here",
                        "click the link",
                        "urgent",
                        "winner",
                        "you won",
                        "claim your prize",
                        "lottery",
                        "cash prize",
                        "refund",
                        "bank",
                        "upi",
                        "payment failed",
                        "send money",
                        "transfer money"
                    )

                    val suspiciousLinks = listOf(
                        "bit.ly",
                        "tinyurl",
                        "t.co",
                        "grabify",
                        "http://",
                        "https://"
                    )

                    val keywordMatches =
                        suspiciousKeywords.count {
                            lowerSms.contains(it)
                        }

                    val linkMatches =
                        suspiciousLinks.count {
                            lowerSms.contains(it)
                        }

                    result = when {

                        keywordMatches >= 3 || linkMatches >= 2 ->
                            "🚨 Highly Suspicious SMS"

                        keywordMatches >= 1 || linkMatches >= 1 ->
                            "⚠️ Potentially Suspicious SMS"

                        else ->
                            "✅ SMS appears safe"
                    }

                    scanViewModel.saveScan(
                        type = "SMS",
                        input = sms,
                        result = result
                    )

                    scanning = false
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                if (scanning) "Analyzing..."
                else "🔍 Analyze SMS"
            )
        }

        if (scanning) {

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {

                Text(
                    text = "🔍 Analyzing message for suspicious patterns...",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        if (result.isNotEmpty()) {

            val isDangerous = result.contains("🚨")
            val isWarning = result.contains("⚠️")

            val resultTitle = when {
                isDangerous -> "High Risk Message"
                isWarning -> "Potential Risk Detected"
                else -> "No Suspicious Patterns"
            }

            val resultDescription = when {
                isDangerous ->
                    "This message contains several indicators commonly associated with scams or phishing."

                isWarning ->
                    "This message contains at least one pattern that may indicate a suspicious message."

                else ->
                    "No suspicious patterns were detected by the current SMS analyzer."
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = when {
                        isDangerous ->
                            MaterialTheme.colorScheme.errorContainer

                        isWarning ->
                            MaterialTheme.colorScheme.tertiaryContainer

                        else ->
                            MaterialTheme.colorScheme.primaryContainer
                    }
                )
            ) {

                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    Text(
                        text = when {
                            isDangerous -> "🚨"
                            isWarning -> "⚠️"
                            else -> "✅"
                        },
                        style = MaterialTheme.typography.headlineMedium
                    )

                    Text(
                        text = resultTitle,
                        style = MaterialTheme.typography.titleLarge
                    )

                    Text(
                        text = resultDescription,
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )

                    Text(
                        text = result,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}
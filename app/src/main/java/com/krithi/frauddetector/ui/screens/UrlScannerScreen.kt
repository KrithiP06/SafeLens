package com.krithi.frauddetector.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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

@Composable
fun UrlScannerScreen(
    navController: NavController,
    scanViewModel: ScanViewModel
) {

    var url by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    var scanning by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        ScreenHeader(
            title = "Scan URL",
            onBack = {
                navController.popBackStack()
            }
        )

        Text(
            text = "Check a website for known security threats.",
            style = MaterialTheme.typography.bodyMedium
        )

        OutlinedTextField(
            value = url,
            onValueChange = { url = it },
            label = {
                Text("Enter Website URL")
            },
            placeholder = {
                Text("https://example.com")
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            enabled = !scanning && url.isNotBlank(),
            onClick = {

                scanning = true
                result = ""

                scanViewModel.checkUrl(
                    url = url
                ) { apiResult ->

                    result = apiResult

                    scanViewModel.saveScan(
                        type = "URL",
                        input = url,
                        result = apiResult
                    )

                    scanning = false
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                if (scanning) "Scanning..."
                else "🔍 Scan URL"
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
                    text = "🔍 Checking with Google Safe Browsing...",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        if (result.isNotEmpty()) {

            val isDangerous = result.contains("🚨")
            val isWarning = result.contains("⚠️")

            val resultTitle = when {
                isDangerous -> "Threat Detected"
                isWarning -> "Unable to Complete Scan"
                else -> "No Known Threats"
            }

            val resultDescription = when {
                isDangerous ->
                    "Google Safe Browsing identified this URL as potentially dangerous."

                isWarning ->
                    "The URL could not be completely verified. Try again later."

                else ->
                    "Google Safe Browsing did not find any known threats for this URL."
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
                        text = url,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}
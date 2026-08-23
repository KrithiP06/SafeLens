package com.krithi.frauddetector.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.krithi.frauddetector.viewmodel.ScanViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun HistoryScreen(
    scanViewModel: ScanViewModel
) {

    val scanHistory by scanViewModel.scanHistory.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Text(
            text = "Scan History",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "${scanHistory.size} scan(s)",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (scanHistory.isEmpty()) {

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "📭",
                    style = MaterialTheme.typography.displaySmall
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "No scans yet",
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Your URL, SMS and phone number scans will appear here.",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

        } else {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {

                OutlinedButton(
                    onClick = {
                        scanViewModel.clearHistory()
                    }
                ) {
                    Text("Clear All")
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(
                    items = scanHistory,
                    key = { it.id }
                ) { scan ->

                    val dateFormat =
                        SimpleDateFormat(
                            "dd MMM yyyy, hh:mm a",
                            Locale.getDefault()
                        )

                    val formattedDate =
                        dateFormat.format(Date(scan.timestamp))

                    val resultColor =
                        when {
                            scan.result.contains("🚨") ->
                                MaterialTheme.colorScheme.error

                            scan.result.contains("⚠️") ->
                                MaterialTheme.colorScheme.tertiary

                            else ->
                                MaterialTheme.colorScheme.primary
                        }

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor =
                                MaterialTheme.colorScheme.surfaceVariant
                        )
                    ) {

                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {

                                Text(
                                    text = scan.type,
                                    style = MaterialTheme.typography.titleMedium
                                )

                                Text(
                                    text = formattedDate,
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }

                            Text(
                                text = scan.input,
                                style = MaterialTheme.typography.bodyMedium
                            )

                            Text(
                                text = scan.result,
                                color = resultColor,
                                style = MaterialTheme.typography.titleSmall
                            )

                            Button(
                                onClick = {
                                    scanViewModel.deleteScan(scan.id)
                                }
                            ) {
                                Text("Delete")
                            }
                        }
                    }
                }
            }
        }
    }
}
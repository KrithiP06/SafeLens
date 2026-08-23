package com.krithi.frauddetector.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(
    darkMode: Boolean,
    onDarkModeChange: (Boolean) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Settings",
            style = MaterialTheme.typography.headlineLarge
        )

        Text(
            text = "Customize your SafeLens experience.",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(
            modifier = Modifier.height(4.dp)
        )

        Text(
            text = "Appearance",
            style = MaterialTheme.typography.titleMedium
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = Icons.Default.DarkMode,
                        contentDescription = "Dark Mode"
                    )

                    Spacer(
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )

                    Column {

                        Text(
                            text = "Dark Mode",
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text(
                            text = if (darkMode)
                                "Dark theme enabled"
                            else
                                "Light theme enabled",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }

                Switch(
                    checked = darkMode,
                    onCheckedChange = onDarkModeChange
                )
            }
        }

        Text(
            text = "About",
            style = MaterialTheme.typography.titleMedium
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {

            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = Icons.Default.Security,
                        contentDescription = "SafeLens"
                    )

                    Spacer(
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )

                    Column {

                        Text(
                            text = "SafeLens",
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text(
                            text = "See Beyond Scams",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "Version"
                    )

                    Spacer(
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )

                    Text(
                        text = "Version 1.0",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

        Spacer(
            modifier = Modifier.weight(1f)
        )

        Text(
            text = "Stay alert. Think before you click.",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}
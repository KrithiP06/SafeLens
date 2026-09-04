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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(
    darkMode: Boolean,
    onDarkModeChange: (Boolean) -> Unit
) {

    // SafeLens accent colors
    val purpleAccent = Color(0xFF8B7CFF)
    val greenAccent = Color(0xFF52D9A0)

    // Theme-aware colors
    val backgroundColor = if (darkMode) {
        Color(0xFF080B14)
    } else {
        MaterialTheme.colorScheme.background
    }

    val glassColor = if (darkMode) {
        Color(0xFF151A2A).copy(alpha = 0.88f)
    } else {
        MaterialTheme.colorScheme.surface
    }

    val glassBorder = if (darkMode) {
        Color(0xFF343B55).copy(alpha = 0.75f)
    } else {
        MaterialTheme.colorScheme.outline.copy(alpha = 0.35f)
    }

    val primaryText = if (darkMode) {
        Color.White
    } else {
        MaterialTheme.colorScheme.onSurface
    }

    val secondaryText = if (darkMode) {
        Color(0xFF9EA6BD)
    } else {
        MaterialTheme.colorScheme.onSurfaceVariant
    }

    val mutedText = if (darkMode) {
        Color(0xFF777F98)
    } else {
        MaterialTheme.colorScheme.onSurfaceVariant
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(
                start = 20.dp,
                top = 20.dp,
                end = 20.dp,
                bottom = 32.dp
            ),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {

        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Palette,
                contentDescription = null,
                tint = purpleAccent
            )

            Spacer(
                modifier = Modifier.padding(horizontal = 5.dp)
            )

            Text(
                text = "Settings",
                style = MaterialTheme.typography.headlineLarge,
                color = primaryText
            )
        }

        Text(
            text = "Customize your SafeLens experience.",
            style = MaterialTheme.typography.bodyLarge,
            color = secondaryText
        )

        // Appearance card
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
                    .padding(18.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.Default.Palette,
                    contentDescription = null,
                    tint = purpleAccent
                )

                Spacer(
                    modifier = Modifier.padding(horizontal = 7.dp)
                )

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = "Appearance",
                        style = MaterialTheme.typography.titleMedium,
                        color = primaryText
                    )

                    Spacer(
                        modifier = Modifier.height(3.dp)
                    )

                    Text(
                        text = if (darkMode) {
                            "Dark mode is enabled"
                        } else {
                            "Light mode is enabled"
                        },
                        style = MaterialTheme.typography.bodySmall,
                        color = mutedText
                    )
                }

                Switch(
                    checked = darkMode,
                    onCheckedChange = {
                        onDarkModeChange(it)
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White,
                        checkedTrackColor = purpleAccent,
                        uncheckedThumbColor = Color.White,
                        uncheckedTrackColor = MaterialTheme.colorScheme.outline
                    )
                )
            }
        }

        // About SafeLens
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
                modifier = Modifier.padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        tint = purpleAccent
                    )

                    Spacer(
                        modifier = Modifier.padding(horizontal = 7.dp)
                    )

                    Text(
                        text = "About SafeLens",
                        style = MaterialTheme.typography.titleMedium,
                        color = primaryText
                    )
                }

                Text(
                    text = "SafeLens",
                    style = MaterialTheme.typography.titleLarge,
                    color = primaryText
                )

                Text(
                    text = "See Beyond Scams",
                    style = MaterialTheme.typography.bodyMedium,
                    color = greenAccent
                )

                Text(
                    text = "A mobile security tool designed to help identify suspicious phone numbers, websites, and messages.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = secondaryText
                )

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (darkMode) {
                            Color(0xFF0D111D).copy(alpha = 0.78f)
                        } else {
                            MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f)
                        }
                    ),
                    border = BorderStroke(
                        1.dp,
                        if (darkMode) {
                            Color(0xFF30364D)
                        } else {
                            MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                        }
                    )
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = "Version",
                            style = MaterialTheme.typography.bodyMedium,
                            color = mutedText
                        )

                        Text(
                            text = "1.0",
                            style = MaterialTheme.typography.bodyMedium,
                            color = primaryText
                        )
                    }
                }
            }
        }

        Spacer(
            modifier = Modifier.weight(1f)
        )

        // Footer
        Text(
            text = "SafeLens • See Beyond Scams",
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.bodySmall,
            color = mutedText
        )
    }
}